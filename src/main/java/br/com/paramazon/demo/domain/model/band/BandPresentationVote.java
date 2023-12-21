package br.com.paramazon.demo.domain.model.band;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "band_presentation_vote")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BandPresentationVote implements Serializable {

    @Id
    @Column(nullable = false, name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBandPresentationVote;

    @Column
    private Integer votes;

    @Column
    @Builder.Default
    private boolean enabled = false;

    @OneToOne(fetch = FetchType.LAZY)
    private BandPresentation bandPresentation;

}
