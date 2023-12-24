package br.com.paramazon.demo.application.dto.user.profile;

import br.com.paramazon.demo.application.dto.media.MediaDTO;
import br.com.paramazon.demo.application.dto.user.profile.preferences.UserPreferenceDTO;
import br.com.paramazon.demo.application.dto.user.validations.ValidationEmailDTO;
import br.com.paramazon.demo.application.dto.user.role.RoleDTO;
import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.*;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "User Profile", value = "User Profile", description = "Usuario do sistema para uso.")
public record UserProfileDTO(

        @ApiModelProperty(value = "identificador do Usuario no sistema.", example = "1", required = false)
        Long idUser,
        @ApiModelProperty(value = "Usuario que foi cadastrado no sistema.", example = "teste123", required = true)
        String username,
        @ApiModelProperty(value = "Foto do usuario cadastrada no sistema.", example = "teste123", required = true)
        MediaDTO photograph,
        @ApiModelProperty(value = "E-mail cadastrado do Usuario", example = "teste@teste.com.br")
        ValidationEmailDTO email,
        @ApiModelProperty(value = "Bandas preferidas do Usuario.")
        Set<UserPreferenceDTO> preferences,
        @ApiModelProperty(value = "Niveis de acesso do Usuario.")
        Set<RoleDTO> roles) {

}