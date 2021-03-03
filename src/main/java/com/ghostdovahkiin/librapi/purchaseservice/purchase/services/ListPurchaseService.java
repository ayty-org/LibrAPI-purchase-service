package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;

import java.util.List;

@FunctionalInterface
public interface ListPurchaseService {
    List<PurchaseReturnDTO> findAll();
}
