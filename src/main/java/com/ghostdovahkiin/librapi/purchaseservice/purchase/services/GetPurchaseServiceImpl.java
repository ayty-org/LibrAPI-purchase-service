package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.exceptions.PurchaseNotFoundException;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetBook;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetUser;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.UserDTO;
import com.ghostdovahkiin.librapi.purchaseservice.utils.PurchaseJoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetPurchaseServiceImpl implements GetPurchaseService{
    private final PurchaseJoinRepository purchaseJoinRepository;
    private final GetBook getBook;
    private final GetUser getUser;

    @Override
    public PurchaseReturnDTO findByID(Long id) {
        List<PurchaseReturnDTO> purchaseReturnDTOList = purchaseJoinRepository.findAllPurchase(getUser, getBook);
        return purchaseReturnDTOList.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .collect(Collectors.toList()).get(0);
    }
}
