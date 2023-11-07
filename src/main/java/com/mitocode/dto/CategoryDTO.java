package com.mitocode.dto;


import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Integer idCategory;

    @NotNull                           //NO ACEPTA UN VALOR NULO               *CUANDO NO MANDAS EL CAMPO*
    @NotEmpty                         //NO ACEPTA QUE EL CAMPO ESTÉ VACIO      *CUANDO NO LLENAS EL CAMPO*
    @Size(min = 3, max = 50)         //LO CARACTERES MÍNIMOS Y MÁXIMOS         *PERMITIDO SOLAMENTE PARA CAMPOS DE TIPO STRING*
  //@Pattern(regexp = "[A-Za-z]*")  //USO DE LAS EXPRESIONES REGULARES        *BUSCAR TIPOS DE EXPRESIONES REGULARES*
    private String nameCategory;

    @Size(min = 4, max = 150)
    private String descriptionCategory;
    @NotNull
    private boolean enabledCategory;

//    @Max(value = 20)   ÉSTAS ANOTACIONES SIRVEN PARA CAMPOS DE TIPOS INTEGER
//    @Min(value = 10
//    @Email             TIENE QUE RESPETAR LA ESTRUCTURA DE UN EMAIL

}
