package br.com.paramazon.demo.domain.model.show.band.bandMember;

import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter @Setter @ToString
@NoArgsConstructor @Builder
@Entity @Table(name = "band_singer")
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BandMember implements Serializable {

    @EmbeddedId
    private BandMemberId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("bandId")
    @JoinColumn(name = "band_id")
    @JsonBackReference
    private Band band;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "payment", nullable = false)
    private Double payment;

}
