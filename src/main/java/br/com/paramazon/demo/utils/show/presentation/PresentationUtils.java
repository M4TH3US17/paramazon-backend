package br.com.paramazon.demo.utils.show.presentation;

import br.com.paramazon.demo.application.dto.show.presentation.PresentationDTO;
import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import br.com.paramazon.demo.infrastructure.request.shows.presentation.RegisterPresentationRequest;
import br.com.paramazon.demo.utils.media.MediaUtils;
import br.com.paramazon.demo.utils.show.band.BandUtils;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PresentationUtils {

    /**
     * Converte uma lista de entidades do tipo Presentation em uma lista de entidades do tipo PresentationDTO.
     *
     * @param presentationList Um conjunto de entidades Presentation.
     * @return Um conjunto de PresentationDTO.
     */
    public static List<PresentationDTO> buildBasePresentationList(List<Presentation> presentationList) {
        return  presentationList.stream()
                .map(presentation -> convertToDTO(presentation))
                .collect(Collectors.toList());
    }

    /**
     * Converte uma unica entidade do tipo Presentation em PresentationDTO.
     *
     * @param data Uma entidade Presentation.
     * @return Um PresentationDTO.
     */
    public static PresentationDTO convertToDTO(Presentation data) {
        return new PresentationDTO(
                data.getIdPresentation(),
                BandUtils.convertToDTO(data.getBand()),
                MediaUtils.convertToDTO(data.getPhotograph()),
                data.getStartPresentation(),
                data.getEndPresentation(),
                data.getDescription());
    }

    /**
     * Trata os dados da requisição, valida antes de persistir no banco.
     *
     * @param request Uma entidade RegisterPresentationRequest.
     * @return Um Presentation ou null se a requisição estiver incompleta ou contiver dados inválidos.
     */
    public static Presentation makePresentationToPersist(RegisterPresentationRequest request, Band presentationBand) {
        return Presentation.builder()
                .band(presentationBand)
                .description(request.description())
                .endPresentation(request.endPresentation())
                .startPresentation(request.startPresentation())
                .photograph(MediaUtils.makeMediaToPersist(request.photograph()))
                .build();
    }
}
