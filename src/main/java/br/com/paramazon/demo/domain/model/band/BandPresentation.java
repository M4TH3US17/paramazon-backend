package br.com.paramazon.demo.domain.model.band;

import br.com.paramazon.demo.domain.model.singer.Singer;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@Builder
@Entity
@Table(name = "band_presentation")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BandPresentation implements Serializable {

    @Id
    @Column(nullable = false, name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBandPresentation;

    @Column
    @Builder.Default
    private boolean enabled = false;

    @Column
    private String showImage;

    @Column(nullable = false)
    private LocalTime startPresentation;

    @Column(nullable = false)
    private LocalTime endPresentation;

    @OneToOne
    private Band band;

}
