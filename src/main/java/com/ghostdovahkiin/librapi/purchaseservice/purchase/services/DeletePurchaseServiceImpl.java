package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.exceptions.PurchaseNotFoundException;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePurchaseServiceImpl implements DeletePurchaseService{
    private final PurchaseRepository purchaseRepository;

    @Override
    public void delete(Long id) {
        if (purchaseRepository.existsById(id)){
            purchaseRepository.deleteById(id);
        }else {
            throw new PurchaseNotFoundException();
        }
    }
}
