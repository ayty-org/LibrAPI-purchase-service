package com.ghostdovahkiin.librapi.purchaseservice.builder;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.CategoryDTO;

public class CategoryBuilder {
    public static CategoryDTO.Builder createCategory() {
        return CategoryDTO
                .builder()
                .name("Technology");
    }
}
