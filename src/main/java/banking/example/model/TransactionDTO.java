package banking.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDTO {

    // Getters and Setters
    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("operation_type_id")
    private Long operationTypeId;

    @JsonProperty("amount")
    private Double amount;

}
