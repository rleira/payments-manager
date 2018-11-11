package com.dlocal.paymentsmanager.datastore.dal;

import com.dlocal.paymentsmanager.datastore.models.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, String> {
}
