package br.com.paramazon.demo.application.dto.show.band;

import br.com.paramazon.demo.application.dto.media.MediaDTO;
import br.com.paramazon.demo.application.dto.music.MusicDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Band", value = "Band", description = "Banda do sistema para uso.")
public record BandDTO(
        Long idBand,
        String name,
        MediaDTO photograph,
        Double totalPayment,
        String description,
        Set<MusicDTO> playlist,
        List<BandMemberDTO> members
) implements Serializable {

}