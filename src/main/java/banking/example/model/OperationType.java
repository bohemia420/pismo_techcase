package banking.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
public class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationTypeId;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private OperationTypeEnum description;

    public OperationType() {
    }

    public OperationType(OperationTypeEnum description) {
        this.description = description;
    }

}