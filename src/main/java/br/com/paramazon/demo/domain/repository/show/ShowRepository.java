package br.com.paramazon.demo.domain.repository.show;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findAllByStatus(Status status);
    Optional<Show> findByIdShowAndStatus(Long id, Status status);

    boolean existsShowByDate(LocalDate date);
}
