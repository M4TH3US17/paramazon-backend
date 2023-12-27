package br.com.paramazon.demo.domain.model.show.showVote;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.showVote.presentationVote.PresentationVote;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter @Setter
@ToString @NoArgsConstructor
@Builder
@Entity @Table(name = "show_vote") @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowVote implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShowVote;

    @Column(name = "start_voting", nullable = false)
    private LocalDateTime startVoting;

    @Column(name = "end_voting", nullable = false)
    private LocalDateTime endVoting;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "total_votes", nullable = false)
    private Integer totalVotes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "show_vote_presentation_vote",
            joinColumns = @JoinColumn(name = "show_vote_id"),
            inverseJoinColumns = @JoinColumn(name = "presentation_vote_id"))
    private List<PresentationVote> presentationVotes;

    @PrePersist
    private void prePersist() {
        this.totalVotes = 0; /* CRIAR UM ALGORITMO PRA SOMAR TODOS OS VOTOS */
    }

    public List<PresentationVote> getShowVoteResult() {
        return new ArrayList<>();
    }

}
