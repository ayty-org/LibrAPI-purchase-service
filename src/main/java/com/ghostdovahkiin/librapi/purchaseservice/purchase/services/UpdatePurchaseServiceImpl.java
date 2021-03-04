package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.exceptions.PurchaseNotFoundException;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePurchaseServiceImpl implements UpdatePurchaseService{
    private final PurchaseRepository purchaseRepository;

    @Override
    public void update(PurchaseUpdateDTO purchaseUpdateDTO, Long id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(PurchaseNotFoundException::new);
        purchase.setStatus(purchaseUpdateDTO.getStatus());
        purchaseRepository.save(purchase);
    }
}
