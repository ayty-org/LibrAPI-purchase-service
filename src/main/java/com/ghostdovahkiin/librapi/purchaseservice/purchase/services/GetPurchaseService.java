package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;

@FunctionalInterface
public interface GetPurchaseService {
    PurchaseReturnDTO findByID(Long id);
}
