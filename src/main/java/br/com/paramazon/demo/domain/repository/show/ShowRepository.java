package br.com.paramazon.demo.domain.repository.show;

import br.com.paramazon.demo.domain.model.show.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
}
