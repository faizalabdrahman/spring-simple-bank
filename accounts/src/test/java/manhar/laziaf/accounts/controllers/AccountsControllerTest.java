package manhar.laziaf.accounts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import manhar.laziaf.accounts.services.AccountService;
import manhar.laziaf.accounts.web.dto.AccountDto;
import manhar.laziaf.accounts.web.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AccountsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AccountService accountService;

    @Test
    void createAccountTest() throws Exception {
        CustomerDto customerDto = getValidCustomerDto();
        String customerDtoToJson = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(post("/api/v1/accounts/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDtoToJson))
                .andExpect(status().isCreated());
    }

    @Test
    void getAccountDetailsTest() throws Exception {
        CustomerDto customerDto = getValidCustomerDto();
        customerDto.setAccountDto(getValidAccountDto());
        given(accountService.getAccount(anyString())).willReturn(customerDto);

        mockMvc.perform(get("/api/v1/accounts/get")
                        .param("mobileNumber", anyString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    CustomerDto getValidCustomerDto() {
        return CustomerDto.builder()
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .name("John Doe")
                .email("johndoe@outlook.com")
                .mobileNumber("123456789")
                .build();
    }

    AccountDto getValidAccountDto() {
        return AccountDto.builder()
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .accountType("SAVINGS")
                .accountNumber(1L)
                .branchAddress("Some Address")
                .build();
    }
}