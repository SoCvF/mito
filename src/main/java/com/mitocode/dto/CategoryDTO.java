package com.mitocode.dto;


import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Integer idCategory;

    @NotNull @NotEmpty @Size(min = 3, max = 50)
    private String nameCategory;

    @Size(min = 4, max = 150)
    private String descriptionCategory;

    @NotNull
    private boolean enabledCategory;

}
