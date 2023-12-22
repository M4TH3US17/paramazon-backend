package br.com.paramazon.demo.application.dto;

import br.com.paramazon.demo.domain.model.show.band.bandSinger.BandSingerId;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Band Singer", value = "Band Singer", description = "Cantores vinculados a bandas")
public class BandSingerDTO implements Serializable {

    private BandSingerId id;
    private BandDTO band;
    private UserProfileDTO user;
    private Double payment;
}
