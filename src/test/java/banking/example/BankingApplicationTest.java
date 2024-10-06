package banking.example;

import banking.example.model.Account;
import banking.example.repository.AccountRepository;
import banking.example.repository.OperationTypeRepository;
import banking.example.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BankingApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertThat(accountRepository).isNotNull();
    }

    @Test
    void testAccountRepositoryBeanExists() {
        assertThat(applicationContext.getBean(AccountRepository.class)).isNotNull();
    }

    @Test
    void testTransactionRepositoryBeanExists() {
        assertThat(applicationContext.getBean(TransactionRepository.class)).isNotNull();
    }

    @Test
    void testOperationTypeRepositoryBeanExists() {
        assertThat(applicationContext.getBean(OperationTypeRepository.class)).isNotNull();
    }

    @Autowired
    private AccountRepository accountRepository;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        accountRepository.deleteAll();
    }

    @Test
    void shouldCreateAccount() throws Exception {
        String accountJson = "{\"document_number\": \"12345678900\"}";

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountJson))
                .andExpect(status().isOk());

        assertThat(accountRepository.count()).isEqualTo(1);
    }

    @Test
    void shouldReturnAccountWhenExists() throws Exception {
        Account account = new Account();
        account.setDocumentNumber("12345678900");
        accountRepository.save(account);

        mockMvc.perform(get("/api/accounts/" + account.getAccountId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.document_number").value("12345678900"));
    }

    @Test
    void shouldReturnNotFoundForMissingAccount() throws Exception {
        mockMvc.perform(get("/api/accounts/999999"))
                .andExpect(status().isNotFound());
    }
}
