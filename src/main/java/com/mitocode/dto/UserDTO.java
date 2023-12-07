package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @NotNull
    private Integer idUser;

    @NotNull
    private RoleDTO role;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 70)
    @JsonProperty(value = "user_name")//Esta anotación hace que tanto en el request como en el response se muestre el campo user_name
    private String username;

    @NotNull @NotEmpty @Size(min = 10, max = 60)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//Esta anotación hace que el password no se muestre en el JSON
    private String password;

    @NotNull
   // @JsonProperty(access = JsonProperty.Access.READ_ONLY)//Esta anotación hace que en el request no se pueda enviar el campo enabled
    private boolean enabled;

}
