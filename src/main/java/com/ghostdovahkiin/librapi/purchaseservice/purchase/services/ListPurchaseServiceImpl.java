package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.feign.GetBook;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetUser;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPurchaseServiceImpl implements ListPurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final GetBook getBook;
    private final GetUser getUser;

    @Override
    public List<PurchaseReturnDTO> findAll() {
        List<PurchaseReturnDTO> purchaseReturnDTOList;
        return purchaseReturnDTOList =  purchaseRepository.findAllPurchase(getUser, getBook);
    }
}

