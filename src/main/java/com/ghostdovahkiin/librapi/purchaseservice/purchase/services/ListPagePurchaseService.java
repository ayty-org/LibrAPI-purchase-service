package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPagePurchaseService {
    Page<PurchaseReturnDTO> findPurchase(Pageable pageable);
}
