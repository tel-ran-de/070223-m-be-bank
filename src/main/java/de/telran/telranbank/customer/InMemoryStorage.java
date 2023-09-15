package de.telran.telranbank.customer;

public interface InMemoryStorage {

    void put(CustomerJson customerJson);

    CustomerJson get(String login);
}
