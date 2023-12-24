package br.com.paramazon.demo.application.dto.music;

import br.com.paramazon.demo.domain.enums.MusicalStyle;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Music", value = "Music", description = "Nomes de musicas")
public record MusicDTO (
        Long idMusic,
        String name,
        MusicalStyle style
) implements Serializable {

    @Override
    public int hashCode() {
        return Objects.hash(idMusic, name, style);
    }
}
