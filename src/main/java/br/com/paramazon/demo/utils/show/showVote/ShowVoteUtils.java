package br.com.paramazon.demo.utils.show.showVote;

import br.com.paramazon.demo.application.dto.show.ShowVoteDTO;
import br.com.paramazon.demo.domain.model.show.showVote.ShowVote;
import br.com.paramazon.demo.utils.show.showVote.presentationVote.PresentationVoteUtils;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShowVoteUtils {

    /**
     * Converte uma lista de entidades do tipo ShowVote em uma lista de entidades do tipo ShowVoteDTO.
     *
     * @param showVotes conjunto de entidades ShowVote.
     * @return Um conjunto de ShowVoteDTO.
     */
    public static List<ShowVoteDTO> buildBaseShowVoteList(List<ShowVote> showVotes) {
        return showVotes.stream()
                .map(data -> convertToDTO(data))
                .collect(Collectors.toList());
    }

    /**
     * Converte uma unica entidade ShowVote em ShowVoteDTO.
     *
     * @param data Uma entidades ShowVote.
     * @return Um ShowVoteDTO.
     */
    public static ShowVoteDTO convertToDTO(ShowVote data) {
        System.out.println(data.getPresentationVotes());
        return new ShowVoteDTO(
                data.getIdShowVote(),
                data.getStartVoting(),
                data.getEndVoting(),
                data.getTotalVotes(),
                PresentationVoteUtils.buildBasePresentationVoteList(data.getPresentationVotes()));
    }



}
