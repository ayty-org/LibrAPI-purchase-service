package com.ghostdovahkiin.librapi.purchaseservice.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class BookDTO {

    private static final long serialVersionUID = 675638275324538545L;

    @NotNull
    private long id;

    @NotNull
    private String specificID;

    @NotNull(message = "ISBN cannot be null")
    @Size(min = 17, max = 17, message = "ISBN must have 17 characters like: 978-3-16-148410-0")
    private String isbn;

    @Size(min = 1, max = 50, message = "Title must be between 1 and 50 characters")
    @NotNull(message = "Book Title cannot be null")
    private String title;

    @Size(min = 1, max = 120, message = "Synopsis must be between 1 and 120 characters")
    @NotNull(message = "Synopsis cannot be null")
    private String synopsis;

    @NotNull(message = "Author cannot be null")
    private String author;

    @NotNull(message = "Publication Year cannot be null")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate publicationYear;

    @NotNull(message = "Sell Price cannot be null")
    @Min(0)
    private double sellPrice;

    @Min(0)
    @NotNull(message = "Available quantity cannot be null")
    private int availableQuantity;

    @NotNull
    private Set<CategoryDTO> category;
}
