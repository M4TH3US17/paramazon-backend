package br.com.paramazon.demo.infrastructure.response.shows;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "ShowResponse", value = "ShowResponse", description = "Resposta de Show do sistema para uso.")
public record ShowResponse (
        @ApiModelProperty(value = "Código HttpStatus", example = "200, 201, 400...", required = true)
        int code,

        @ApiModelProperty(value = "Mensagem de retorno do sistema", example = "Show Cadastrado com sucesso.", required = true)
        String message,

        @ApiModelProperty(value = "Objeto de retorno para o front-end", example = "Pode ser diversas entidades, conforme o fluxo atual.")
        Object data
) implements Serializable {
}