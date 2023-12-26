package br.com.paramazon.demo.utils.show;

import br.com.paramazon.demo.application.dto.show.ShowDTO;
import br.com.paramazon.demo.domain.model.show.Show;
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

}
