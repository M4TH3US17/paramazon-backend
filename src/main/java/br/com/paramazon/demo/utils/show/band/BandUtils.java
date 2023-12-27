package br.com.paramazon.demo.utils.show.band;

import br.com.paramazon.demo.application.dto.show.band.BandDTO;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.utils.media.MediaUtils;
import br.com.paramazon.demo.utils.music.MusicUtils;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BandUtils {


    /**
     * Converte uma lista de entidades do tipo Band em uma lista de entidades do tipo BandDTO.
     *
     * @param bands Um conjunto de entidades Band.
     * @return Um conjunto de BandDTO.
     */
    public static Set<BandDTO> buildBaseBandList(List<Band> bands) {
        return  bands.stream()
                .map(band -> convertToDTO(band))
                .collect(Collectors.toSet());
    }

    /**
     * Converte uma unica entidade banda do tipo Band em BandDTO.
     *
     * @param data Uma entidades Band.
     * @return Um BandDTO.
     */
    public static BandDTO convertToDTO(Band data) {
        //Set<BandSinger> members = data.getBandSingers().stream().map(BandSinger::getUser).collect(Collectors.toSet());

        return new BandDTO(
                data.getIdBand(),
                data.getName(),
                MediaUtils.convertToDTO(data.getPhotograph()),
                data.getTotalPayment(),
                data.getDescription(),
                MusicUtils.buildBaseMusicList(data.getPlaylist()),
                BandMemberUtils.buildBaseParticipantsList(new ArrayList<>(data.getBandMembers()))
        );
    }
}
