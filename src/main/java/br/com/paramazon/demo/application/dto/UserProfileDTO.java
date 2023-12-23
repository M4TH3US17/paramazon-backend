package br.com.paramazon.demo.application.dto;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.*;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "User Profile", value = "User Profile", description = "Usuario do sistema para uso.")
public record UserProfileDTO(
        @ApiModelProperty(value = "Usuario que cadastrado no sistema.", example = "teste123", required = true)
        String username,
        @ApiModelProperty(value = "Foto do usuario cadastrada no sistema.", example = "teste123", required = true)
        MediaDTO photograph,
        @ApiModelProperty(value = "E-mail cadastrado do Usuario", example = "teste@teste.com.br")
        ValidationEmailDTO email,
        @ApiModelProperty(value = "Bandas preferidas do Usuario.")
        Set<BandDTO> preferences) {


}