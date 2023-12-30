package br.com.paramazon.demo.infrastructure.request.shows.presentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Register Presentation Vote Request",
        value = "Register Presentation Vote Request",
        description = "Entidade RegisterPresentationVoteRequest para cadastrar presentation no sistema."
)
public record RegisterPresentationVoteRequest(
         Long idPresentation
) {
}
