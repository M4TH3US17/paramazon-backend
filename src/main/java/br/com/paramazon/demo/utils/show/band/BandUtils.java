package br.com.paramazon.demo.utils.show.band;

import br.com.paramazon.demo.application.dto.show.band.BandDTO;
import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.infrastructure.request.shows.band.RegisterBandRequest;
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

    /**
     * Trata os dados da requisição antes de salvar no banco.
     *
     * @param request Uma entidade RegisterBandRequest.
     * @return Uma entidade Band.
     */
    public static Band makeBandToPersist(RegisterBandRequest request) {
        return Band.builder()
                .idBand(request.idBand())
                .name(request.name())
                .description(request.description())
                .photograph(MediaUtils.makeMediaToPersist(request.photograph()))
                .build();
    }
}
