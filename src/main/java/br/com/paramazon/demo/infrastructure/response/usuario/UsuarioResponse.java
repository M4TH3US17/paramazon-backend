package br.com.paramazon.demo.infrastructure.response.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResponse {

    @ApiModelProperty(value = "Código HttpStatus", example = "200, 201, 400...", required = true)
    private int code;

    @ApiModelProperty(value = "Mensagem de retorno do sistema", example = "Usuario Cadastrado com sucesso.", required = true)
    private String message;

    @ApiModelProperty(value = "Objeto de retorno para o front-end", example = "Pode ser diversas entidades, conforme o fluxo atual.")
    private Object data;
}