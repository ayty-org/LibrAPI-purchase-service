package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.feign.GetBook;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetUser;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import com.ghostdovahkiin.librapi.purchaseservice.utils.PurchaseJoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListPagePurchaseServiceImpl implements ListPagePurchaseService{
    private final PurchaseJoinRepository purchaseJoinRepository;
    private final GetBook getBook;
    private final GetUser getUser;

    @Override
    public Page<PurchaseReturnDTO> findPurchase(Pageable pageable) {
        List<PurchaseReturnDTO> purchaseReturnDTOList =  purchaseJoinRepository.findAllPurchase(getUser, getBook);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), purchaseReturnDTOList.size());
        return new PageImpl(purchaseReturnDTOList.subList(start, end), pageable, purchaseReturnDTOList.size());
    }
}
