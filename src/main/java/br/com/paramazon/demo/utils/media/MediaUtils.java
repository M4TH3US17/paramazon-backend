package br.com.paramazon.demo.utils.media;

import br.com.paramazon.demo.application.dto.media.MediaDTO;
import br.com.paramazon.demo.domain.model.media.Media;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MediaUtils {

    /**
     * Converte uma lista de entidades do tipo Media em uma lista de entidades do tipo MediaDTO.
     *
     * @param medias Um conjunto de entidades Media.
     * @return Um conjunto de MediaDTO.
     */
    public static List<MediaDTO> buildBaseMediaList(List<Media> medias) {
        return  medias.stream()
                .map(band -> convertToDTO(band))
                .collect(Collectors.toList());
    }

    /**
     * Converte uma unica entidade midia (video ou imagem) do tipo Media em MediaDTO.
     *
     * @param data Uma entidades Media.
     * @return Um MediaDTO.
     */
    public static MediaDTO convertToDTO(Media data) {
        return new MediaDTO(
                data.getIdMedia(),
                data.getS3Key()
        );
    }
}
