package br.com.paramazon.demo.application.dto.show.band;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Band Singer", value = "Band Singer", description = "Cantores vinculados a bandas")
public record BandMemberDTO(
       // BandMemberId id,
        String username,
        String photograph,
        Double payment
) implements Serializable {

}