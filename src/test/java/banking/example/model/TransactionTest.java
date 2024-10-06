package banking.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class TransactionTest {

    @Test
    void testTransactionCreation() {
        Account account = new Account();
        OperationType operationType = new OperationType(OperationTypeEnum.NORMAL_PURCHASE);
        Transaction transaction = new Transaction();

        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setAmount(-100.0);

        Assertions.assertEquals(account, transaction.getAccount());
        Assertions.assertEquals(operationType, transaction.getOperationType());
        Assertions.assertEquals(-100.0, transaction.getAmount());
    }
}
