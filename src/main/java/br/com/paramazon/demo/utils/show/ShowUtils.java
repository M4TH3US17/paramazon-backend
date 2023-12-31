package br.com.paramazon.demo.utils.show;

import br.com.paramazon.demo.application.dto.show.ShowDTO;
import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.Show;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.show.band.bandMember.BandMember;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import br.com.paramazon.demo.infrastructure.request.shows.RegisterShowRequest;
import br.com.paramazon.demo.utils.show.presentation.PresentationUtils;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShowUtils {

    /**
     * Converte uma lista de entidades do tipo Show em uma lista de entidades do tipo ShowDTO.
     *
     * @param shows Um conjunto de entidades Show.
     * @return Um conjunto de ShowDTO.
     */
    public static Set<ShowDTO> buildBaseShowList(List<Show> shows) {
        return shows.stream()
                .map(show -> convertToDTO(show))
                .collect(Collectors.toSet());
    }

    /**
     * Converte uma unica entidade banda do tipo Show em ShowDTO.
     *
     * @param data Uma entidades Show.
     * @return Um ShowDTO.
     */
    public static ShowDTO convertToDTO(Show data) {
        return new ShowDTO(
                data.getIdShow(),
                data.getDate(),
                data.getBudget(),
                PresentationUtils.buildBasePresentationList(data.getPresentations()));
    }

    public static Show makeShowToPersist(RegisterShowRequest request, List<Presentation> presentationList) {
        return Show.builder()
                .status(Status.ACTIVE)
                .budget(calculateShowBudget(presentationList))
                .presentations(presentationList)
                .date(request.date())
                .build();
    }

    public static Double calculateShowBudget(List<Presentation> presentations) {
        if((Objects.isNull(presentations) || presentations.isEmpty())) return 0.0;

        return presentations.stream().mapToDouble(presentation -> presentation.getBand().getTotalPayment()).sum();
    }
}
