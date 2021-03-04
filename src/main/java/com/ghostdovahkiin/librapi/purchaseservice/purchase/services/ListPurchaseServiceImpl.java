package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.feign.GetBook;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetUser;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import com.ghostdovahkiin.librapi.purchaseservice.utils.PurchaseJoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPurchaseServiceImpl implements ListPurchaseService {
    private final PurchaseJoinRepository purchaseJoinRepository;
    private final GetBook getBook;
    private final GetUser getUser;

    @Override
    public List<PurchaseReturnDTO> findAll() {
        return purchaseJoinRepository.findAllPurchase(getUser, getBook);
    }
}

