package com.mybytes.springsection2.config;

import com.mybytes.springsection2.model.Customer;
import com.mybytes.springsection2.repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyBankUserDetails implements UserDetailsService {


    CustomerRepository customerRepository;

    public MyBankUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        // GrantedAuthority is form spring security - e.c. read, write ...
        List<GrantedAuthority> authorities = null;
        List<Customer> customer = customerRepository.findAllByEmail(username);
        if (customer.size() == 0) {
            throw new UsernameNotFoundException(String.format("User details not found for the user:%s", username));
        } else {
            userName = customer.get(0).getEmail();
            password = customer.get(0).getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
        }
        // Returns a user with the fetched values from the db (it's an existing user in the system form our custom security db)
        return new User(userName, password, authorities);
    }
}
