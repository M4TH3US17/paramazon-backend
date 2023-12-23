package br.com.paramazon.demo.infrastructure.request.users;

import br.com.paramazon.demo.domain.model.user.role.Role;
import br.com.paramazon.demo.infrastructure.request.users.validationEmail.RegisterEmailRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Register User Request",
        value = "Register User Request",
        description = "Entidade RegisterUserRequest para cadastrar usuario no sistema."
)
public record RegisterUserRequest(
        @ApiModelProperty(value = "Usuario que será cadastrado no sistema.", example = "username123", required = true)
        String username,

        @ApiModelProperty(value = "Senha que será cadastrado no sistema.", example = "12345678", required = true)
        String password,

        @ApiModelProperty(value = "Senha que será cadastrado no sistema.", example = "12345678", required = true)
        String repeatPassword,

        @ApiModelProperty(value = "E-mail para ser validado", example = "")
        RegisterEmailRequest email,

        @ApiModelProperty(value = "Role para definir nivel de acesso do Usuario.", example = "USER")
        Role role
) {
}