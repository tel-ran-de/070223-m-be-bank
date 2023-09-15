package de.telran.telranbank.customer;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerEntityRepository extends CrudRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByLogin(String login);
}
