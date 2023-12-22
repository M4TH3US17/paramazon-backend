package br.com.paramazon.demo.application.dto;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "User Profile", value = "User Profile", description = "Usuario do sistema para uso.")
public class UserProfileDTO implements Serializable  {

    @ApiModelProperty(value = "Usuario que cadastrado no sistema.", example = "teste123", required = true)
    private String username;

    @ApiModelProperty(value = "Foto do usuario cadastrada no sistema.", example = "teste123", required = true)
    private MediaDTO photograph;

    @ApiModelProperty(value = "E-mail cadastrado do Usuario", example = "teste@teste.com.br")
    private ValidationEmailDTO email;

    @ApiModelProperty(value = "Bandas preferidas do Usuario.")
    private Set<BandDTO> preferences;


}
