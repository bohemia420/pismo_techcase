package banking.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

class OperationTypeTest {

    @Test
    void testOperationTypeCreation() {
        OperationType operationType = new OperationType(OperationTypeEnum.NORMAL_PURCHASE);
        Assertions.assertNull(operationType.getOperationTypeId());
        assertEquals(OperationTypeEnum.NORMAL_PURCHASE, operationType.getDescription());
    }

    @Test
    void testOperationTypeSetters() {
        OperationType operationType = new OperationType();
        operationType.setDescription(OperationTypeEnum.CREDIT_VOUCHER);

        assertEquals(OperationTypeEnum.CREDIT_VOUCHER, operationType.getDescription());
    }
}
