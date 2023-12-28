package br.com.paramazon.demo.infrastructure.request.shows.presentation;

import br.com.paramazon.demo.application.dto.show.band.BandDTO;
import br.com.paramazon.demo.infrastructure.request.medias.RegisterMediaRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Register Presentation Request",
        value = "Register Presentation Request",
        description = "Entidade RegisterPresentationRequest para cadastrar presentation no sistema."
)
public record RegisterPresentationRequest(
        Long idBand,
        RegisterMediaRequest photograph,
        LocalTime startPresentation,
        LocalTime endPresentation,
        String description
) {
}
