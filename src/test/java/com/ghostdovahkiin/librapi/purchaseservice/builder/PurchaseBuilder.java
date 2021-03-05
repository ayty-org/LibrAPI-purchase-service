package com.ghostdovahkiin.librapi.purchaseservice.builder;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Status;

public class PurchaseBuilder {
    public static Purchase.Builder createPurchase() {
        return Purchase
                .builder()
                .user("69661bd1-6092-4068-bd28-c60517f8a16b")
                .id(123L)
                .amountToPay(229.0)
                .status(Status.CLOSED)
                .specificID("69661bd1-6092-4068-bd28-c60517f8a16b")
                .books("fe07d7bb-2cac-4c47-b9f0-19aa2df60949");
    }
}
