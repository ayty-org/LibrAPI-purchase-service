package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseSaveDTO;

@FunctionalInterface
public interface SavePurchaseService {
    void insert(PurchaseSaveDTO purchaseSaveDTO);
}
