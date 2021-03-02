package com.ghostdovahkiin.librapi.purchaseservice.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class CategoryDTO {
    /**
     *
     */
    private static final long serialVersionUID = 675672465324534545L;

    private long id;

    @NotNull(message = "Category name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
}
