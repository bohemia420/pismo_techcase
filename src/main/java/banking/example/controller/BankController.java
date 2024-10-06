package banking.example.controller;

import banking.example.model.*;
import banking.example.repository.AccountRepository;
import banking.example.repository.OperationTypeRepository;
import banking.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    // 2. GET /accounts/:accountId
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        OperationType operationType = operationTypeRepository.findById(transactionDTO.getOperationTypeId())
                .orElseThrow(() -> new RuntimeException("Operation Type not found"));

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        if (operationType.getDescription() == OperationTypeEnum.NORMAL_PURCHASE ||
                operationType.getDescription() == OperationTypeEnum.WITHDRAWAL ||
                operationType.getDescription() == OperationTypeEnum.PURCHASE_WITH_INSTALLMENTS) {
            // Set to negative for purchase and withdrawal
            transaction.setAmount(-Math.abs(transactionDTO.getAmount()));
        } else if (operationType.getDescription() == OperationTypeEnum.CREDIT_VOUCHER) {
            // Set to positive for credit voucher
            transaction.setAmount(Math.abs(transactionDTO.getAmount()));
        } else {
            // Optionally, handle other operation types or throw an exception
            throw new RuntimeException("Invalid Operation Type for transaction.");
        }

        return transactionRepository.save(transaction);
    }
}
