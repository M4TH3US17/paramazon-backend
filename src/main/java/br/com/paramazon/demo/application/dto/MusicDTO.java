package br.com.paramazon.demo.application.dto;

import br.com.paramazon.demo.domain.enums.MusicalStyle;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Music", value = "Music", description = "Nomes de musicas")
public record MusicDTO(
        Long idMusic,
        String name,
        MusicalStyle style
) {
}
