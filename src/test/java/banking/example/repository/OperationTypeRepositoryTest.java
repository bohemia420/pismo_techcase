package banking.example.repository;

import banking.example.model.OperationType;
import banking.example.model.OperationTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OperationTypeRepositoryTest {

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    @Test
    void testSaveAndFindOperationType() {
        OperationType operationType = new OperationType(OperationTypeEnum.PURCHASE_WITH_INSTALLMENTS);

        OperationType savedOperationType = operationTypeRepository.save(operationType);
        assertNotNull(savedOperationType.getOperationTypeId());

        OperationType foundOperationType = operationTypeRepository.findById(savedOperationType.getOperationTypeId()).orElse(null);
        assert foundOperationType != null;
        assertEquals(OperationTypeEnum.PURCHASE_WITH_INSTALLMENTS, foundOperationType.getDescription());
    }
}
