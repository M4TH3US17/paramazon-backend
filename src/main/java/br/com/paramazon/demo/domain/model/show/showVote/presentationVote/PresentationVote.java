package br.com.paramazon.demo.domain.model.show.showVote.presentationVote;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@ToString @NoArgsConstructor
@Builder
@Entity @Table(name = "presentation_vote") @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PresentationVote implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPresentationVote;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id")
    private Presentation votingPresentation;

    @Column(nullable = false)
    private Integer votes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
