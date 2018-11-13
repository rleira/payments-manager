package com.dlocal.paymentsmanager.datastore.dal;

import com.dlocal.paymentsmanager.datastore.models.FixerIO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixerIORepository extends PagingAndSortingRepository<FixerIO, String> {
    public List<FixerIO> findTop1ByOrderByTimestampDesc();
}
