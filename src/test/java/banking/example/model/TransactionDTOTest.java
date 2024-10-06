package banking.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

class TransactionDTOTest {

    @Test
    void testTransactionDTO() {
        TransactionDTO dto = new TransactionDTO();
        dto.setAccountId(1L);
        dto.setOperationTypeId(1L);
        dto.setAmount(100.50);

        Assertions.assertEquals(1L, dto.getAccountId());
        Assertions.assertEquals(1L, dto.getOperationTypeId());
        Assertions.assertEquals(100.50, dto.getAmount());
    }
}
