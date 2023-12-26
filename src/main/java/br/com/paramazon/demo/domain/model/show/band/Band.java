package br.com.paramazon.demo.domain.model.show.band;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.media.Media;
import br.com.paramazon.demo.domain.model.music.Music;
import br.com.paramazon.demo.domain.model.show.band.bandMember.BandMember;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Getter @Setter
@ToString @NoArgsConstructor
@Builder
@Entity @Table(name = "band") @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Band implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBand;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private Media photograph;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "total_payment", nullable = false)
    private Double totalPayment;

    @Column
    private String description;

    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "band_playlist",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id"))
    private Set<Music> playlist;

    @OneToMany(mappedBy = "band", fetch = FetchType.LAZY)
    private Set<BandMember> bandMembers;

    @PrePersist
    private void prePersist() {
        this.createDate = LocalDate.now();
    }

    public void addBandSinger(BandMember bandSinger) {
        bandMembers.add(bandSinger);
        bandSinger.setBand(this);
    }

    public void removeBandSinger(BandMember bandSinger) {
        bandMembers.remove(bandSinger);
        bandSinger.setBand(null);
    }

}
