package br.com.paramazon.demo.domain.repository.show.band;

import br.com.paramazon.demo.domain.model.show.band.bandSinger.BandSinger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandSingerRepository extends JpaRepository<BandSinger, Long> {
}
