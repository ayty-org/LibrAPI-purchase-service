package com.ghostdovahkiin.librapi.purchaseservice.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class PurchaseReturnDTO {
    private long id;
    private String specificID;
    private UserDTO user;
    private List<BookDTO> books;
    private double amountToPay;
    private Status status;

    public static PurchaseReturnDTO from(Purchase entity, UserDTO user, List<BookDTO> books) {
        return PurchaseReturnDTO
                .builder()
                .id(entity.getId())
                .specificID(entity.getSpecificID())
                .user(user)
                .books(books)
                .amountToPay(entity.getAmountToPay())
                .status(entity.getStatus())
                .build();
    }
}
