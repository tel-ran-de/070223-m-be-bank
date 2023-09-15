package de.telran.telranbank.customer;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerStorage customerStorage;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerJson customerJson, HttpServletRequest httpServletRequest) {
        if (customerJson.getLogin() == null) {
            return ResponseEntity.status(400).body(null);
        }
        customerStorage.put(new CustomerEntity(customerJson.getLogin(), customerJson.getAddress(),
                customerJson.getFirstName(), customerJson.getLastName(), customerJson.getEmail()));
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/customer/{login}", method = RequestMethod.GET)
    public ResponseEntity<CustomerJson> getCustomer(@PathVariable("login") String login) {
        CustomerEntity customerEntity = customerStorage.get(login);
        return ResponseEntity.ok(new CustomerJson(customerEntity.getLogin(), customerEntity.getAddress(),
                customerEntity.getFirstName(), customerEntity.getLastName(), customerEntity.getEmail()));
    }
}
