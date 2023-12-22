package br.com.paramazon.demo.domain.model.show.showVote;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.showVote.presentationVote.PresentationVote;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class ShowVote implements Serializable {

    private Long idShowVote;
    private LocalDateTime startVoting;
    private LocalDateTime endVoting;
    private Status status;
    private List<PresentationVote> presentationVotes = new ArrayList<>();

    public Integer getTotalVotes() {
        return 0;
    }

    public List<PresentationVote> getShowVoteResult() {
        return new ArrayList<>();
    }

}
