package banking.example;

import banking.example.model.OperationType;
import banking.example.model.OperationTypeEnum;
import banking.example.repository.OperationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        operationTypeRepository.save(new OperationType(OperationTypeEnum.NORMAL_PURCHASE));
        operationTypeRepository.save(new OperationType(OperationTypeEnum.WITHDRAWAL));
        operationTypeRepository.save(new OperationType(OperationTypeEnum.CREDIT_VOUCHER));
        operationTypeRepository.save(new OperationType(OperationTypeEnum.PURCHASE_WITH_INSTALLMENTS));
    }
}