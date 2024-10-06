package banking.example.repository;

import banking.example.model.OperationTypeEnum;
import banking.example.model.Transaction;
import banking.example.model.Account;
import banking.example.model.OperationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testSaveAndFindTransaction() {
        Account account = new Account();
        account.setDocumentNumber("12345678900");

        OperationType operationType = new OperationType(OperationTypeEnum.NORMAL_PURCHASE);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setAmount(-50.0);

        Transaction savedTransaction = transactionRepository.save(transaction);
        assertNotNull(savedTransaction.getTransactionId());

        Transaction foundTransaction = transactionRepository.findById(savedTransaction.getTransactionId()).orElse(null);
        assert foundTransaction != null;
        assertEquals(-50.0, foundTransaction.getAmount());
    }
}
