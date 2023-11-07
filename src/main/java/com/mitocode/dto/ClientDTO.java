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
@JsonInclude(JsonInclude.Include.NON_NULL) //Si hay respuestas de servicios HTTP/REST cuyo valor sea nulo se va a ignorar
                                           //O sea al momento de pedir el servicio y encuentre campos en nulo no los va a mostrar
public class ClientDTO {

    @Min(value = 1)
    private Integer idClient;
    @NotNull @NotEmpty @Size(min = 3)
    private String firstName;
    @NotNull @NotEmpty @Size(min = 3, max = 100)
    private String lasName;
    @NotNull @NotEmpty @Size(min = 10, max = 10)
    private String carId;
    @NotNull @NotEmpty @Pattern(regexp = "[0-9]+")
    private String phoneNumber;
    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty @Size(min = 10, max = 100)
    private String address;



}
