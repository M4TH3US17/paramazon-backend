package br.com.paramazon.demo.application.dto.show.band;

import br.com.paramazon.demo.application.dto.user.profile.UserProfileDTO;
import br.com.paramazon.demo.domain.model.show.band.bandSinger.BandSingerId;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Band Singer", value = "Band Singer", description = "Cantores vinculados a bandas")
public record BandSingerDTO(
        BandSingerId id,
        BandDTO band,
        UserProfileDTO user,
        Double payment
) implements Serializable {

}