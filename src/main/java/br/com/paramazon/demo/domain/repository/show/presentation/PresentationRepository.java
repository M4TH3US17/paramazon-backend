package br.com.paramazon.demo.domain.repository.show.presentation;

import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long> {
}
