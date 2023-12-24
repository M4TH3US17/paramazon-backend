package br.com.paramazon.demo.utils.music;

import br.com.paramazon.demo.application.dto.music.MusicDTO;
import br.com.paramazon.demo.domain.model.music.Music;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MusicUtils {

    /**
     * Converte uma lista de entidades do tipo Music em uma lista de entidades do tipo MusicDTO.
     *
     * @param musicList Um conjunto de entidades Music.
     * @return Um conjunto de MusicDTO.
     */
    public static Set<MusicDTO> buildBaseMusicList(Set<Music> musicList) {
       return  musicList.stream()
                .map(music -> convertToDTO(music))
                .collect(Collectors.toSet());
    }

    /**
     * Converte uma unica entidade musica do tipo Music em MusicDTO.
     *
     * @param data Uma entidades Music.
     * @return Um MusicDTO.
     */
    public static MusicDTO convertToDTO(Music data) {
        return new MusicDTO(
                data.getIdMusic(),
                data.getName(),
                data.getStyle());
    }

}
