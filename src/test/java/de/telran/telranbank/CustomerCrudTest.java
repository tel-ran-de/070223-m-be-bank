package de.telran.telranbank;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.telranbank.customer.CustomerEntity;
import de.telran.telranbank.customer.CustomerEntityRepository;
import de.telran.telranbank.customer.CustomerJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/schema-cleanup.sql")
@Sql("/schema.sql")
public class CustomerCrudTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Test
    @WithMockUser
    public void shouldCreateAccount() throws Exception {
        // given
        CustomerJson customerJson = new CustomerJson("antonermak",
                "Thailand",
                "Anton",
                "Ermak",
                "antonermak@gmail.com");

        String customerStr = objectMapper.writeValueAsString(customerJson);

        // when
        MvcResult accountCreationResult = mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerStr))
                .andReturn();

        MvcResult accountGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/customer/" +
                        customerJson.getLogin()))
                .andReturn();

        // then
        Assertions.assertEquals(200, accountCreationResult.getResponse().getStatus());
        Assertions.assertEquals(200, accountGetResult.getResponse().getStatus());

        String accountGetStringJson = accountGetResult.getResponse().getContentAsString();
        CustomerJson receivedCustomerJson = objectMapper.readValue(accountGetStringJson, CustomerJson.class);

        Assertions.assertEquals(customerJson, receivedCustomerJson);
    }

    //
    @Test
    @WithMockUser
    public void shouldDeleteCustomer() throws Exception {
        // given
        customerEntityRepository.save(new CustomerEntity("superman", "Some Address",
                "Ivan", "Petrov", "ivan@gmail.com"));


        // when
        MvcResult customerDeleteResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/superman")
                        .with(csrf()))
                .andReturn();


        // then
        Assertions.assertEquals(200, customerDeleteResult.getResponse().getStatus());
        Assertions.assertFalse(customerEntityRepository.findByLogin("superman").isPresent());
    }

    @Test
    @WithMockUser
    public void shouldDiscardInvalidCustomer() throws Exception {
        // given
        CustomerJson customerJson = new CustomerJson("antonermak",
                "Thailand",
                "Anton",
                "Ermak",
                "antonermak@telran.de");

        String customerStr = objectMapper.writeValueAsString(customerJson);

        // when
        MvcResult customerCreationResult = mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerStr))
                .andReturn();

        // then
        Assertions.assertEquals(400, customerCreationResult.getResponse().getStatus());
        Assertions.assertFalse(customerEntityRepository.findByLogin("antonermak").isPresent());
    }


}
