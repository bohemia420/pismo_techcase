package banking.example.repository;

import banking.example.model.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {
}