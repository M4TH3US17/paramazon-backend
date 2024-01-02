package br.com.paramazon.demo.utils.media;

import br.com.paramazon.demo.application.dto.media.MediaDTO;
import br.com.paramazon.demo.domain.enums.MediaType;
import br.com.paramazon.demo.domain.model.media.Media;
import br.com.paramazon.demo.infrastructure.request.medias.RegisterMediaRequest;
import lombok.*;

import java.util.*;
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
                .map(media -> convertToDTO(media))
                .collect(Collectors.toList());
    }

    /**
     * Converte uma unica entidade midia (video ou imagem) do tipo Media em MediaDTO.
     *
     * @param data Uma entidades Media.
     * @return Um MediaDTO.
     */
    public static MediaDTO convertToDTO(Media data) {
        if(Objects.isNull(data)) return null;

        return new MediaDTO(
                data.getIdMedia(),
                data.getS3Key()
        );
    }

    /**
     * Trata os dados da requisição antes de salvar no banco.
     *
     * @param request Uma entidade RegisterMediaRequest.
     * @return Uma entidade Media.
     */
    public static Media makeMediaToPersist(RegisterMediaRequest request) {
        return Media.builder()
                .s3Key(request.s3Key())
                .type(MediaType.IMAGE)
                .build();
    }
}
