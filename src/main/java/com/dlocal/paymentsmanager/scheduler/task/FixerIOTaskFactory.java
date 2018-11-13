package com.dlocal.paymentsmanager.scheduler.task;

import com.dlocal.paymentsmanager.datastore.dal.FixerIORepository;
import com.dlocal.paymentsmanager.datastore.dal.PaymentRepository;
import com.dlocal.paymentsmanager.datastore.enums.PaymentCurrency;
import com.dlocal.paymentsmanager.datastore.enums.PaymentStatus;
import com.dlocal.paymentsmanager.datastore.models.FixerIO;
import com.dlocal.paymentsmanager.datastore.models.Payment;
import com.dlocal.paymentsmanager.services.FixerIOService;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.OptionalLong;

@Service
public class FixerIOTaskFactory {

    @Autowired
    private FixerIORepository fixerIORepository;

    @Autowired
    private PaymentRepository paymentsRepository;

    @Autowired
    private FixerIOService fixerIOService;

    public FixerIORunnable getFixerIORunnable() {
        return new FixerIORunnable();
    }

    private class FixerIORunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("FixerIOTask running!");

            Iterator<Payment> pendingCurrencyPaymentIterator = paymentsRepository
                    .findPaymentsByPaymentStatusAndAmountUSDAndCurrencyNotOrderByTimestampAsc(
                            PaymentStatus.PENDING,
                            null,
                            PaymentCurrency.USD
                    )
                    .iterator();
            if (pendingCurrencyPaymentIterator.hasNext()) {

                ArrayList<Payment> pendingCurrencyPaymentList = Lists.newArrayList(pendingCurrencyPaymentIterator);

                OptionalLong maxTimestampOptional = pendingCurrencyPaymentList
                        .stream()
                        .mapToLong(payment -> payment.getTimestamp())
                        .max();

                if (maxTimestampOptional.isPresent()) {
                    try {
                        FixerIO fixerIO = getLatestFixerIO(maxTimestampOptional.getAsLong());
                        setUSDCurrencyFromFixerIOForEachPendingCurrency(
                                pendingCurrencyPaymentList,
                                fixerIO
                        );
                    } catch (IOException e) {
                        System.out.println(e.getStackTrace());
                        System.out.println(e.getMessage());
                        return;
                    }
                }
            }
            System.out.println("FixerIOTask ending!!");
        }

        private FixerIO getLatestFixerIO(long maxtimestamp) throws IOException {
            FixerIO latestFixerIO = null;
            Iterator<FixerIO> latestFixerIOIterator = fixerIORepository
                    .findTop1ByOrderByTimestampDesc()
                    .iterator();

            if (!latestFixerIOIterator.hasNext()) {
                latestFixerIO = queryLatestFixerIO();
            }
            else {
                latestFixerIO = latestFixerIOIterator.next();
                if (latestFixerIO.getTimestamp() < maxtimestamp) {
                    latestFixerIO = queryLatestFixerIO();
                }
            }

            return latestFixerIO;
        }

        private FixerIO queryLatestFixerIO() throws IOException {
            FixerIO latestFixerIO = fixerIOService.getCurrencyFromFixerIO();
            fixerIORepository.save(latestFixerIO);
            return latestFixerIO;
        }

        private void setUSDCurrencyFromFixerIOForEachPendingCurrency(
                final ArrayList<Payment> pendingCurrencyPaymentList,
                final FixerIO fixerIO
        ){
            final Double usdValue = fixerIO.getRates().get(PaymentCurrency.USD.toString());
            pendingCurrencyPaymentList
                    .stream()
                    .forEach(pendingPayment -> {
                        Double currencyValue = fixerIO.getRates().get(pendingPayment.getCurrency().toString());
                        pendingPayment.setAmountUSD((pendingPayment.getAmount()/usdValue)*currencyValue);
                        paymentsRepository.save(pendingPayment);
                        System.out.println("FixerIOTask, pendingPayment: " + pendingPayment);
                    });
        }
    }
}
