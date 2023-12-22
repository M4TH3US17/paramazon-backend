package br.com.paramazon.demo.infrastructure.request.users.validationEmail;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Register Email Validation Request",
        value = "Register Email Validation Request",
        description = "Entidade RegisterValidationEmailRequest para cadastrar no usuario a ser inserido sistema."
)
public class RegisterEmailRequest {


    @ApiModelProperty(value = "E-mail a ser cadastrado", example = "teste@teste.com.br")
    private String email;

    @ApiModelProperty(value = "Codigo aleatorio de confimacao", example = "778899")
    private Integer verificationCode;

}
