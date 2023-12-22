package br.com.paramazon.demo.utils.show.band;

import br.com.paramazon.demo.application.dto.BandDTO;
import br.com.paramazon.demo.domain.model.show.band.Band;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BandUtils {


    public static Set<BandDTO> buildBaseBandList(Set<Band> bands) {
        return  bands.stream()
                .map(band -> convertToDTO(band))
                .collect(Collectors.toSet());
    }

    public static BandDTO convertToDTO(Band data) {
        return BandDTO.builder().build();
    }
}
