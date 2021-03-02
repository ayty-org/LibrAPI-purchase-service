package com.ghostdovahkiin.librapi.purchaseservice.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class PurchaseSaveDTO {
    private long id;
    private String user;
    private Set<String> books;
    private double amountToPay;
    private Status status;
    private String specificID;
}
