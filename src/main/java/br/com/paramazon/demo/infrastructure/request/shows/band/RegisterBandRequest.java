package br.com.paramazon.demo.infrastructure.request.shows.band;

import br.com.paramazon.demo.infrastructure.request.medias.RegisterMediaRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Register Band Request",
        value = "Register Band Request",
        description = "Entidade RegisterBandRequest para cadastrar banda no sistema.")
public record RegisterBandRequest(
        Long idBand,
        String name,
        RegisterMediaRequest photograph,
        String description
        //Set<Music> playlist,
        //Set<BandMember> bandMembers,
) {
}
