package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDetailDTO {

    @JsonBackReference //Esta anotación hará referencia a la clase padre, en este caso SaleDTO
    private SaleDTO sale;

    @NotNull
    private ProductDTO product;

    @NotNull @Min(value = 1)
    private short quantity;

    @NotNull @Min(value = 1)
    private double salePrice;

    @NotNull @Min(value = 1)
    private  double discount;

}
