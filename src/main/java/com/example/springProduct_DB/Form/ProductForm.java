package com.example.springProduct_DB.Form;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductForm {
    private int id;
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    @DecimalMin(value = "0.0")
    private int price;
}
