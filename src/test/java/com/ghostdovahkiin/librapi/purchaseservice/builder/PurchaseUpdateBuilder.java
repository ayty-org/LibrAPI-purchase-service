package com.ghostdovahkiin.librapi.purchaseservice.builder;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseUpdateDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Status;

public class PurchaseUpdateBuilder {
    public static PurchaseUpdateDTO.Builder createPurchaseUpdate() {
        return PurchaseUpdateDTO.builder().status(Status.COMPLETED);
    }
}
