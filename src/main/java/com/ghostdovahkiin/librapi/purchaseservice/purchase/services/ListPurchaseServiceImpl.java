package com.ghostdovahkiin.librapi.purchaseservice.purchase.services;

import com.ghostdovahkiin.librapi.purchaseservice.feign.GetBook;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetUser;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.BookDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPurchaseServiceImpl implements ListPurchaseService {
    private final PurchaseRepository purchaseRepository;
    private List<PurchaseReturnDTO> purchases;
    private final GetBook getBook;
    private final GetUser getUser;

    @Override
    public List<PurchaseReturnDTO> findAll() {
        purchases = new ArrayList<>();
        for (Purchase purchase : purchaseRepository.findAll()) {
            UserDTO userDTO = getUser.findBySpecificID(purchase.getUser());
            List<String> booksID = Arrays.asList(purchase.getBooks().split(","));
            List<BookDTO> booksFound = new ArrayList<>();
            for(String book : booksID) {
                System.out.println("Book 1: " + getBook.findBySpecificID(book).getAuthor());
                booksFound.add(getBook.findBySpecificID(book));
            }
            purchases.add(PurchaseReturnDTO.from(purchase, userDTO, booksFound));
        }
        return purchases;
    }
}

