package br.com.paramazon.demo.infrastructure.request.users.validationEmail;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.*;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Register Email Validation Request",
        value = "Register Email Validation Request",
        description = "Entidade RegisterValidationEmailRequest para cadastrar no usuario a ser inserido sistema."
)
public record RegisterEmailRequest(
        @ApiModelProperty(value = "E-mail a ser cadastrado", example = "teste@teste.com.br")
        String email,

        @ApiModelProperty(value = "Codigo aleatorio de confimacao", example = "778899")
        Integer verificationCode
) {
}