package banking.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "operationTypeId")
    private OperationType operationType;

    @JsonProperty("amount")
    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, updatable = false)
    private LocalDateTime eventDate;

    public Transaction() {
        this.eventDate = LocalDateTime.now();
    }

    public Transaction(Account account, OperationType operationType, Double amount) {
        this.account = account;
        this.operationType = operationType;
        this.amount = amount;
        this.eventDate = LocalDateTime.now();
    }
}
