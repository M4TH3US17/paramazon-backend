package br.com.paramazon.demo.domain.repository.music;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.music.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Set<Music> findAllByIdMusicInAndStatus(Set<Long> longs, Status status);
}
