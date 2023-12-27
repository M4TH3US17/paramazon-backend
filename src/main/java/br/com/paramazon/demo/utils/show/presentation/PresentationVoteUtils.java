package br.com.paramazon.demo.utils.show.presentation;

import br.com.paramazon.demo.application.dto.media.MediaDTO;
import br.com.paramazon.demo.application.dto.show.presentation.PresentationDTO;
import br.com.paramazon.demo.application.dto.show.presentation.PresentationVoteDTO;
import br.com.paramazon.demo.domain.model.media.Media;
import br.com.paramazon.demo.domain.model.show.showVote.presentationVote.PresentationVote;
import br.com.paramazon.demo.utils.show.presentation.PresentationUtils;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PresentationVoteUtils {

    /**
     * Converte uma lista de entidades do tipo PresentationVote em uma lista de entidades do tipo PresentationVoteDTO.
     *
     * @param presentationVotes Um conjunto de entidades PresentationVote.
     * @return Um conjunto de PresentationVoteDTO.
     */
    public static List<PresentationVoteDTO> buildBasePresentationVoteList(List<PresentationVote> presentationVotes) {
        return presentationVotes.stream()
                .map(presentation -> convertToDTO(presentation))
                .collect(Collectors.toList());
    }

    /**
     * Converte uma unica entidade do tipo PresentationVote em PresentationVoteDTO.
     *
     * @param data Uma entidades PresentationVote.
     * @return Um PresentationVoteDTO.
     */
    public static PresentationVoteDTO convertToDTO(PresentationVote data) {
        return new PresentationVoteDTO(
                data.getIdPresentationVote(),
                PresentationUtils.convertToDTO(data.getVotingPresentation()),
                data.getVotes());
    }
}
