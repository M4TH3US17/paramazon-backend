package br.com.paramazon.demo.domain.model.show.band.bandSinger;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class BandSingerId implements Serializable {

    @Column(name = "band_id", nullable = false)
    private Long bandId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
