package br.com.paramazon.demo.infrastructure.request.medias;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;


@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Register Media Request",
        value = "Register Media Request",
        description = "Entidade RegisterMediaRequest para cadastrar arquivos no sistema."
)
public record RegisterMediaRequest(
        String s3Key) {
}
