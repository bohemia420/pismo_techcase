package banking.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

class AccountTest {

    @Test
    void testAccountCreation() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345678900");

        Assertions.assertEquals("12345678900", account.getDocumentNumber());
    }

    @Test
    void testAccountEquality() {
        Account account1 = new Account();
        account1.setAccountId(1L);
        account1.setDocumentNumber("12345678900");

        Account account2 = new Account();
        account2.setAccountId(1L);
        account2.setDocumentNumber("12345678900");

        Assertions.assertEquals(account1.toString(), account2.toString());
    }
}
