package br.com.paramazon.demo.utils.media;

import br.com.paramazon.demo.application.dto.MediaDTO;
import br.com.paramazon.demo.domain.model.media.Media;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MediaUtils {

    public static List<MediaDTO> buildBaseBandList(List<Media> medias) {
        return  medias.stream()
                .map(band -> convertToDTO(band))
                .collect(Collectors.toList());
    }

    public static MediaDTO convertToDTO(Media data) {
        return new MediaDTO(
                data.getIdMedia(),
                data.getS3Key()
        );
    }
}
