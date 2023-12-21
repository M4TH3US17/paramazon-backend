package br.com.paramazon.demo.domain.model.show;

import br.com.paramazon.demo.domain.model.band.BandPresentationVote;
import br.com.paramazon.demo.domain.model.singer.SingerPresentationVote;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@Entity
@Table(name = "show_vote")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowVote implements Serializable {

    @Id
    @Column(nullable = false, name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShowVote;

    @Column
    @Builder.Default
    private boolean enabled = false;

    @Column(nullable = false)
    private LocalDateTime startVoting;

    @Column(nullable = false)
    private LocalDateTime endVoting;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "show_vote_singer_presentation_vote",
            joinColumns = @JoinColumn(name = "show_vote_id"),
            inverseJoinColumns = @JoinColumn(name = "singer_presentation_vote_id"))
    private List<SingerPresentationVote> candidateSingers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "show_vote_band_presentation_vote",
            joinColumns = @JoinColumn(name = "show_vote_id"),
            inverseJoinColumns = @JoinColumn(name = "band_presentation_vote_id"))
    private List<BandPresentationVote> candidateBands;

    public Integer getSumVotes() {
        return 0;
    }

    public List<SingerPresentationVote> getShowVoteResultSingers() {
        return new ArrayList<>();
    }

    public List<BandPresentationVote> getShowVoteResultBands() {
        return new ArrayList<>();
    }
}
