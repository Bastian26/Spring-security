package com.mybytes.springsection2.repository;
import com.mybytes.springsection2.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findbyEmail(String email);
}
