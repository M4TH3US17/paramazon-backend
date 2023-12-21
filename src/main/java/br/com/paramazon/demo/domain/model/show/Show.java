package br.com.paramazon.demo.domain.model.show;

import br.com.paramazon.demo.domain.model.band.BandPresentation;
import br.com.paramazon.demo.domain.model.singer.SingerPresentation;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "show")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Show implements Serializable {

    @Id
    @Column(nullable = false, name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShow;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    @Builder.Default
    private boolean enabled = false;

    @ManyToMany
    @JoinTable(
            name = "show_singer_presentation",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "singer_presentation_id")
    )
    private List<SingerPresentation> singers;

    @ManyToMany
    @JoinTable(
            name = "show_band_presentation",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "band_presentation_id")
    )
    private List<BandPresentation> bands;
}
