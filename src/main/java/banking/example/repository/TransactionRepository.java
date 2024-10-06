package banking.example.repository;

import banking.example.model.Account;
import banking.example.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}