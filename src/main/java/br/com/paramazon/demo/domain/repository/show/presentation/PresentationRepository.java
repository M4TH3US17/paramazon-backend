package br.com.paramazon.demo.domain.repository.show.presentation;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    List<Presentation> findAllByStatus(Status status);
    Optional<Presentation> findByIdPresentationAndStatus(Long id, Status status);
}
