package br.com.paramazon.demo.domain.repository.show.band;

import br.com.paramazon.demo.domain.model.show.band.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
}
