package com.ghostdovahkiin.librapi.purchaseservice.builder;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseSaveDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Status;

import java.util.HashSet;
import java.util.Set;

public class PurchaseSaveBuilder {
    public static PurchaseSaveDTO.Builder createPurchaseSave() {
        Set<String> books = new HashSet<>();
        books.add("fe07d7bb-2cac-4c47-b9f0-19aa2df60949");
        return PurchaseSaveDTO
                .builder()
                .amountToPay(229)
                .id(123L)
                .status(Status.CLOSED)
                .books(books)
                .user("69661bd1-6092-4068-bd28-c60517f8a16b")
                .specificID("5edc11dd-2017-4c20-9d89-cc96970435cb");
    }
}
