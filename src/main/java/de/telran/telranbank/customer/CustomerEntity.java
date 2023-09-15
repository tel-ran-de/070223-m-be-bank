package de.telran.telranbank.customer;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_entity")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_entity_generator")
    @SequenceGenerator(name = "customer_entity_generator", sequenceName = "customer_entity_seq", allocationSize = 1)
    private long id;

    @Column
    private String login;
    @Column
    private String address;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    public CustomerEntity() {
    }

    public CustomerEntity(String login,
                          String address,
                          String firstName,
                          String lastName,
                          String email) {
        this.login = login;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
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
}
