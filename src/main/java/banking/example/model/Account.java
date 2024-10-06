package banking.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @JsonProperty("document_number")
    @Column(nullable = false, unique = true)
    private String documentNumber;

    @Override
    public String toString() {
        return accountId+" "+documentNumber;
    }
}
