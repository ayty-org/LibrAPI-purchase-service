package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.exceptions.BookNotFoundException;
import com.ghostdovahkiin.librapi.purchaseservice.exceptions.UserNotFoundException;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetBook;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetUser;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseSaveDTO;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl implements SavePurchaseService{
    private final PurchaseRepository purchaseRepository;
    private final GetBook getBook;
    private final GetUser getUser;

    @Override
    public void insert(PurchaseSaveDTO purchaseSaveDTO) {
        try{
            getUser.findBySpecificID(purchaseSaveDTO.getUser());
        } catch (FeignException.NotFound req) {
            throw new UserNotFoundException(req.getMessage());
        }

        try {
            for (String books: purchaseSaveDTO.getBooks()){
                getBook.findBySpecificID(books);
            }
        } catch (FeignException.NotFound req) {
            throw new BookNotFoundException(req.getMessage());
        }

        Purchase purchase = Purchase.to(purchaseSaveDTO);
        purchaseRepository.save(purchase);
    }
}
