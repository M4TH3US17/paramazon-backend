package br.com.paramazon.demo.domain.model.singer;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "singer_presentation_vote")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingerPresentationVote implements Serializable {

    @Id
    @Column(nullable = false, name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSingerPresentationVote;

    @Column
    private Integer votes;

    @Column
    @Builder.Default
    private boolean enabled = false;

    @OneToOne(fetch = FetchType.LAZY)
    private SingerPresentation singerPresentation;

}
