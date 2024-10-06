package banking.example.model;

import lombok.Getter;

@Getter
public enum OperationTypeEnum {
    NORMAL_PURCHASE("Normal purchase"),
    WITHDRAWAL("Withdrawal"),
    CREDIT_VOUCHER("Credit voucher"),
    PURCHASE_WITH_INSTALLMENTS("Purchase with installments");

    private final String description;

    OperationTypeEnum(String description) {
        this.description = description;
    }

}
