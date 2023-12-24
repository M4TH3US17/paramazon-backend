package br.com.paramazon.demo.utils.show.band.bandSinger;

import br.com.paramazon.demo.application.dto.show.band.BandSingerDTO;
import br.com.paramazon.demo.domain.model.show.band.bandSinger.BandSinger;
import br.com.paramazon.demo.utils.show.band.BandUtils;
import br.com.paramazon.demo.utils.user.UserUtils;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BandSingerUtils {

    /**
     * Converte uma lista de entidades do tipo BandSinger em uma lista de entidades do tipo BandSingerDTO.
     *
     * @param singers Um conjunto de entidades BandSinger.
     * @return Um conjunto de BandSingerDTO.
     */
    public static Set<BandSingerDTO> buildBaseParticipantsList(Set<BandSinger> singers) {
        return  singers.stream()
                .map(singer -> convertToDTO(singer))
                .collect(Collectors.toSet());
    }

    /**
     * Converte uma unica entidade de cantores do tipo BandSinger em BandSingerDTO.
     *
     * @param data Uma entidades BandSinger.
     * @return Um BandSingerDTO.
     */
    public static BandSingerDTO convertToDTO(BandSinger data) {
        return new BandSingerDTO(
                data.getId(),
                BandUtils.convertToDTO(data.getBand()),
                UserUtils.convertUserToDTO(data.getUser()),
                data.getPayment()
        );
    }

}
