package br.com.paramazon.demo.domain.repository.show.band;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.band.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

    List<Band> findAllByStatus(Status status);
    Optional<Band> findByIdBandAndStatus(Long id, Status status);

    @Query("SELECT COALESCE(MAX(b.idBand), 0) + 1 FROM Band b")
    Long findNextIdBand();
}
