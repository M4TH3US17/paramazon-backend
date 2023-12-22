package br.com.paramazon.demo.domain.repository.music;

import br.com.paramazon.demo.domain.model.music.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
}
