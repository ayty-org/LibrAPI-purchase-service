package com.ghostdovahkiin.librapi.purchaseservice.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

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
    private String specificID = UUID.randomUUID().toString();
}
