package com.example.luyentapapi.repository;

import com.example.luyentapapi.model.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends PagingAndSortingRepository<Address,Long > {
}
