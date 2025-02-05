package com.ghostdovahkiin.librapi.purchaseservice.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@SuppressWarnings("ALL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
@Entity
public class Purchase implements Serializable {
    private static final long serialVersionUID = 923870238720232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String specificID = UUID.randomUUID().toString();

    @Column(name = "user_id")
    private String user;

    private String books;

    private double amountToPay;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public static Purchase to(PurchaseSaveDTO dto, String bookID) {
        return Purchase
                .builder()
                .user(dto.getUser())
                .books(bookID)
                .amountToPay(dto.getAmountToPay())
                .status(dto.getStatus())
                .specificID(dto.getSpecificID())
                .build();
    }

}
