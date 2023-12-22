package br.com.paramazon.demo.domain.model.show.presentation;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.media.Media;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@Getter @Setter
@ToString
@NoArgsConstructor @Builder
@Entity @Table(name = "presentation") @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Presentation implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPresentation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private Media photograph;

    @Column(name = "start_presentation")
    private LocalTime startPresentation;

    @Column(name = "end_presentation")
    private LocalTime endPresentation;

    @Column
    private String description;

}
