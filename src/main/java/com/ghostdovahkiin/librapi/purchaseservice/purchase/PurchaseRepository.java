package com.ghostdovahkiin.librapi.purchaseservice.purchase;

import com.ghostdovahkiin.librapi.purchaseservice.feign.GetBook;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    default List<PurchaseReturnDTO> findAllPurchase(GetUser user, GetBook book) {
        List<PurchaseReturnDTO> purchaseReturnDTOList = new ArrayList<>();
        for(Purchase purchase : this.findAll()){
            UserDTO userDTO = user.findBySpecificID(purchase.getUser());
            String[] booksID = purchase.getBooks().split(",");
            List<BookDTO> booksFound = new ArrayList<>();
            for(String books : booksID) booksFound.add(book.findBySpecificID(books));
            purchaseReturnDTOList.add(PurchaseReturnDTO.from(purchase, userDTO, booksFound));
        }
        return purchaseReturnDTOList;
    }
}
