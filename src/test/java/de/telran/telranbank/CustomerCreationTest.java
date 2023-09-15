package de.telran.telranbank;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.telranbank.customer.CustomerJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerCreationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateAccount() throws Exception {
        // given
        CustomerJson customerJson = new CustomerJson("antonermak",
                "Thailand",
                "Anton",
                "Ermak",
                "antonermak@telran.de");

        String customerStr = objectMapper.writeValueAsString(customerJson);

        // when
        MvcResult accountCreationResult = mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerStr))
                .andReturn();

        MvcResult accountGetResult = mockMvc.perform(MockMvcRequestBuilders.get("/customer/" + customerJson.getLogin()))
                .andReturn();

        // then
        Assertions.assertEquals(200, accountCreationResult.getResponse().getStatus());
        Assertions.assertEquals(200, accountGetResult.getResponse().getStatus());

        String accountGetStringJson = accountGetResult.getResponse().getContentAsString();
        CustomerJson receivedCustomerJson = objectMapper.readValue(accountGetStringJson, CustomerJson.class);

        Assertions.assertEquals(customerJson, receivedCustomerJson);
    }
}
