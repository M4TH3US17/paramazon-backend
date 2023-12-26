package br.com.paramazon.demo.application.dto.show.band;

import br.com.paramazon.demo.application.dto.media.MediaDTO;
import br.com.paramazon.demo.application.dto.music.MusicDTO;
import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.*;

import java.io.Serializable;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Band", value = "Band", description = "Banda do sistema para uso.")
@JsonPropertyOrder({"idBand", "name", "totalPayment", "description", "photograph", "members", "playlist"})
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