package de.telran.telranbank.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomerManagementService {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    public void put(CustomerEntity customer) {
        customerEntityRepository.save(customer);
    }

    public CustomerEntity get(String login) {
        return customerEntityRepository.findByLogin(login).orElse(null);
    }

    @Transactional
    public void delete(String login) {
        customerEntityRepository.deleteByLogin(login);
    }
}
