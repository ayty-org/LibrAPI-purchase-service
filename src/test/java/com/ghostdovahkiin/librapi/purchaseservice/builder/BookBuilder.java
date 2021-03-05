package com.ghostdovahkiin.librapi.purchaseservice.builder;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.BookDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.CategoryDTO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.ghostdovahkiin.librapi.purchaseservice.builder.CategoryBuilder.createCategory;

public class BookBuilder {
    public static BookDTO.Builder createBook() {
        Set<CategoryDTO> categoryDTOS = new HashSet<>();
        categoryDTOS.add(createCategory().build());
        return BookDTO
                .builder()
                .author("John Wick")
                .availableQuantity(500)
                .isbn("978-8-5-8057543-9")
                .publicationYear(LocalDate.of(2014, 8, 14))
                .sellPrice(29.5)
                .specificID("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")
                .title("Percy Jacksu")
                .category(categoryDTOS);
    }
}
