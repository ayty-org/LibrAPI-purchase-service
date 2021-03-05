package com.ghostdovahkiin.librapi.purchaseservice.builder;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.SexDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.UserDTO;

public class UserBuilder {
    public static UserDTO.Builder createUser() {
        return UserDTO
                .builder()
                .age(22)
                .id(123L)
                .email("pedro.sousa@dcx.ufpb.br")
                .phone("+5583986862912")
                .specificID("69661bd1-6092-4068-bd28-c60517f8a16b")
                .sex(SexDTO.MALE);
    }
}
