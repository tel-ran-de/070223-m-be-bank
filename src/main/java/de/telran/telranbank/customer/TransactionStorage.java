package de.telran.telranbank.customer;

import org.springframework.stereotype.Component;

@Component
public class TransactionStorage implements InMemoryStorage {

    @Override
    public void put(CustomerJson customerJson) {

    }

    @Override
    public CustomerJson get(String login) {
        return null;
    }
}
