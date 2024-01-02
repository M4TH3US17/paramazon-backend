package br.com.paramazon.demo.utils.show;

import br.com.paramazon.demo.application.dto.show.ShowVoteDTO;
import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.showVote.ShowVote;
import br.com.paramazon.demo.domain.model.show.showVote.presentationVote.PresentationVote;
import br.com.paramazon.demo.infrastructure.request.shows.RegisterShowVoteRequest;
import br.com.paramazon.demo.utils.show.presentation.PresentationVoteUtils;
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
        return new ShowVoteDTO(
                data.getIdShowVote(),
                data.getStartVoting(),
                data.getEndVoting(),
                data.getTotalVotes(),
                PresentationVoteUtils.buildBasePresentationVoteList(data.getPresentationVotes()));
    }


    public static ShowVote makeShowVoteToPersist(RegisterShowVoteRequest request, List<PresentationVote> presentations) {
        return ShowVote.builder()
                .status(Status.ACTIVE)
                .presentationVotes(presentations)
                .endVoting(request.endVoting())
                .startVoting(request.startVoting())
                .totalVotes(calculateTotalVotes(presentations))
                .build();
    }

    public static int calculateTotalVotes(List<PresentationVote> presentations) {
        return presentations.stream().mapToInt(PresentationVote::getVotes).sum();
    }
}
