package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO {
    @Min(value = 1)
    private Integer idProvider;
    @NotNull @NotEmpty @Size(min = 3, max = 50)
    private String name;
    @NotNull @NotEmpty @Size(min = 3, max = 150)
    private String address;
    @NotNull
    private boolean enabled;



}
