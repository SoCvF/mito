package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Integer idProduct;
    @NotNull @Min(value = 1)
    private Integer idCategory;
    @NotNull @NotEmpty @Size(min = 3, max = 50)
    private String name;
    @NotNull @NotEmpty @Size(min = 3, max = 150)
    private String description;
    @Min(value = 1)
    @Max(value = 9999) //Este será el valor máximo ya que en el MODEL está designado que dos numero serán decimales
    private double price;
    @NotNull
    private boolean enabled;
}
