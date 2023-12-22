package br.com.paramazon.demo.domain.repository.media;

import br.com.paramazon.demo.domain.model.media.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
}
