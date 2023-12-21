package br.com.paramazon.demo.domain.model.band;

import br.com.paramazon.demo.domain.model.singer.Singer;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "band")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Band implements Serializable {

    @Id
    @Column(nullable = false, name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBand;

    @Column
    private String imageBand;

    @Column
    @Builder.Default
    private boolean enabled = false;

    @Column
    private LocalDate registrationDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "band_singer",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "singer_id"))
    private List<Singer> participants;

}
