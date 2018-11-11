package com.dlocal.paymentsmanager.datastore.dal;

import com.dlocal.paymentsmanager.datastore.models.FixerIO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixerIORepository extends PagingAndSortingRepository<FixerIO, String> {
}
