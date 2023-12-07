package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mitocode.model1.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO {

    private Integer idSale;

    @NotNull @JsonIncludeProperties(value = {"idClient"})//Esta anotación hace que solamente se muestre los campos que se están indicando
    private ClientDTO client;

    @NotNull @JsonIncludeProperties(value = {"idUser","username"})
    private UserDTO user;
    @NotNull
    private LocalDateTime dateTime;
    @NotNull @Min(value = 1)
    private double total;
    @NotNull
    private double tax;
    @NotNull
    private boolean enabled;
    @NotNull @JsonManagedReference //Esta anotación hará referencia a la clase hija, en este caso SaleDetailDTO
    private List<SaleDetailDTO> details;



}
