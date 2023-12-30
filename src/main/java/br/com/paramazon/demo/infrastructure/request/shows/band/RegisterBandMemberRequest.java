package br.com.paramazon.demo.infrastructure.request.shows.band;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Register Band Member Request",
        value = "Register Band Member Request",
        description = "Entidade RegisterBandMemberRequest para auxiliar a classe Band.")
public record RegisterBandMemberRequest(
        Long idUser,
        Double payment
) {
}
