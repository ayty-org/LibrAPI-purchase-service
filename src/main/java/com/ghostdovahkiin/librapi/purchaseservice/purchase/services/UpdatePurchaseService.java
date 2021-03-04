package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseUpdateDTO;

@FunctionalInterface
public interface UpdatePurchaseService {
    void update(PurchaseUpdateDTO purchaseUpdateDTO, Long id);
}
