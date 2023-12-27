package br.com.paramazon.demo.application.dto.show;

import br.com.paramazon.demo.application.dto.show.presentation.PresentationVoteDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "ShowVote do sistema para uso.")
public record ShowVoteDTO(
        Long idShowVote,
        LocalDateTime startVoting,
        LocalDateTime endVoting,
        Integer totalVotes,
        List<PresentationVoteDTO> presentationVoteList) {

}
