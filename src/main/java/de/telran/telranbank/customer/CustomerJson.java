package de.telran.telranbank.customer;

import java.util.Objects;

public class CustomerJson {

    private final String login;
    private final String address;
    private final String firstName;
    private final String lastName;
    private final String email;

    public CustomerJson(String login, String address, String firstName, String lastName, String email) {
        this.login = login;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerJson customerJson = (CustomerJson) o;
        return Objects.equals(login, customerJson.login) && Objects.equals(address, customerJson.address) && Objects.equals(firstName, customerJson.firstName) && Objects.equals(lastName, customerJson.lastName) && Objects.equals(email, customerJson.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, address, firstName, lastName, email);
    }
}
