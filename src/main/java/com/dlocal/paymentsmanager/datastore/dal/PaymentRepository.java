package com.dlocal.paymentsmanager.datastore.dal;

import com.dlocal.paymentsmanager.datastore.enums.PaymentCurrency;
import com.dlocal.paymentsmanager.datastore.enums.PaymentStatus;
import com.dlocal.paymentsmanager.datastore.models.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, String> {

    public Iterable<Payment> findPaymentsByPaymentStatus(PaymentStatus paymentStatus);

    public Iterable<Payment> findPaymentsByPaymentStatusAndAmountUSDAndCurrencyNotOrderByTimestampAsc(
            PaymentStatus paymentStatus,
            Double amountUSD,
            PaymentCurrency paymentCurrency
    );

    public Iterable<Payment> findPaymentsByMerchantIdOrderByAmountAsc(String merchantId);
    public Iterable<Payment> findPaymentsByMerchantIdOrderByAmountDesc(String merchantId);

    public Iterable<Payment> findPaymentsByTransactionIdOrderByAmountAsc(String transactionId);
    public Iterable<Payment> findPaymentsByTransactionIdOrderByAmountDesc(String transactionId);

    public Iterable<Payment> findPaymentsByAmountOrderByAmountAsc(Double amount);
    public Iterable<Payment> findPaymentsByAmountOrderByAmountDesc(Double amount);

    public Iterable<Payment> findPaymentsByCurrencyOrderByAmountAsc(PaymentCurrency currency);
    public Iterable<Payment> findPaymentsByCurrencyOrderByAmountDesc(PaymentCurrency currency);

    public Iterable<Payment> findPaymentsByMerchantIdAndTimestampBetween(
            String merchantId,
            int from,
            int to
    );
}
