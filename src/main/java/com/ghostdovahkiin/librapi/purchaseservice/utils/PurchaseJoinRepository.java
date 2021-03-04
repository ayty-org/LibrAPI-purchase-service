package com.ghostdovahkiin.librapi.purchaseservice.utils;

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
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseJoinRepository {
    private final PurchaseRepository purchaseRepository;

    public List<PurchaseReturnDTO> findAllPurchase(GetUser user, GetBook book) {
        List<PurchaseReturnDTO> purchaseReturnDTOList = new ArrayList<>();
        for(Purchase purchase : purchaseRepository.findAll()){
            UserDTO userDTO = user.findBySpecificID(purchase.getUser());
            String[] booksID = purchase.getBooks().split(",");
            List<BookDTO> booksFound = new ArrayList<>();
            for(String books : booksID) booksFound.add(book.findBySpecificID(books));
            purchaseReturnDTOList.add(PurchaseReturnDTO.from(purchase, userDTO, booksFound));
        }
        return purchaseReturnDTOList;
    }
}
