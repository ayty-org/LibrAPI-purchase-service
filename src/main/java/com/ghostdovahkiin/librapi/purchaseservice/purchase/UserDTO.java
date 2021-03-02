package com.ghostdovahkiin.librapi.purchaseservice.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class UserDTO {
    private static final long serialVersionUID = 145485989485039832L;

    private Long id;

    @NotNull
    private String specificID;

    @NotNull(message = "Name cannot be null.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;

    @NotNull(message = "Age cannot be null.")
    @Min(value = 13, message = "Your age must be higher or equal to 13 to use our services.")
    private int age;

    @NotNull
    @Size(min = 8, max = 15, message = "Number must be between 8 and 15 characters.")
    private String phone;

    @NotNull(message = "Email cannot be null.")
    @Email(message = "This email is not valid, please enter a valid email.")
    private String email;

    @NotNull(message = "Sex cannot be null, the values are MALE, FEMALE or UNDEFINED")
    @Enumerated(EnumType.STRING)
    private SexDTO sex;
}
