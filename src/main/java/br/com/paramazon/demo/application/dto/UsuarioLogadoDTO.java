package br.com.paramazon.demo.application.dto;

import br.com.paramazon.demo.application.enums.RoleEnumDTO;
import br.com.paramazon.demo.domain.enums.UsuarioRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Usuario Logado", value = "Usuario Logado", description = "Usuario Logado no sistema para uso.")
public class UsuarioLogadoDTO {

    @ApiModelProperty(value = "Username cadastrado no sistema.", example = "teste123", required = true)
    private String username;

    @ApiModelProperty(value = "E-mail cadastrado do Usuario Logado", example = "teste@teste.com.br", required = true)
    private String email;

   /* @ApiModelProperty(value = "Role para definir nivel de acesso do Usuario.",
            example = "USER", name = "Enumeration Role.", notes = "Enumeration Role.", reference = "Enumeration Role.")
    private Set<UsuarioRole> role;*/

    @ApiModelProperty(value = "Role para definir nivel de acesso do Usuario.", example = "Usuario")
    private Set<RoleEnumDTO> roles;
}
