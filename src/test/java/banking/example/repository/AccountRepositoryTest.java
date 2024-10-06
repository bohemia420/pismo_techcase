package banking.example.repository;

import banking.example.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testSaveAndFindAccount() {
        Account account = new Account();
        account.setDocumentNumber("12345678900");

        Account savedAccount = accountRepository.save(account);
        Assertions.assertNotNull(savedAccount.getAccountId());

        Account foundAccount = accountRepository.findById(savedAccount.getAccountId()).orElse(null);
        assert foundAccount != null;
        Assertions.assertEquals("12345678900", foundAccount.getDocumentNumber());
    }
}
