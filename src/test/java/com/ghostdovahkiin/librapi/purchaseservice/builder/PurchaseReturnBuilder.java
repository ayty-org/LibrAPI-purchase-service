package com.ghostdovahkiin.librapi.purchaseservice.builder;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.BookDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Status;

import java.util.ArrayList;
import java.util.List;

import static com.ghostdovahkiin.librapi.purchaseservice.builder.BookBuilder.createBook;
import static com.ghostdovahkiin.librapi.purchaseservice.builder.UserBuilder.createUser;


public class PurchaseReturnBuilder {
    public static PurchaseReturnDTO.Builder createPurchaseReturn() {
        List<BookDTO> bookDTOList = new ArrayList<>();
        bookDTOList.add(createBook().build());
        bookDTOList.add(createBook().title("meow").specificID("69661bd1-6092-4068-bd28-c60517f8a16s").build());
        return PurchaseReturnDTO
                .builder()
                .id(123L)
                .specificID("69661bd1-6092-4068-bd28-c60517f8a16b")
                .amountToPay(229.0)
                .user(createUser().build())
                .status(Status.DELIVERED)
                .books(bookDTOList);
    }
}
