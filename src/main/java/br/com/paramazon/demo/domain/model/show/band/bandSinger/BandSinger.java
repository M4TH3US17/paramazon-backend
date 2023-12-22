package br.com.paramazon.demo.domain.model.show.band.bandSinger;

import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter @Setter @ToString
@NoArgsConstructor @Builder
@Entity @Table(name = "band_singer")
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BandSinger implements Serializable {

    @EmbeddedId
    private BandSingerId id;

    @ManyToOne
    @MapsId("bandId")
    @JoinColumn(name = "band_id")
    private Band band;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "payment", nullable = false)
    private Double payment;

}
