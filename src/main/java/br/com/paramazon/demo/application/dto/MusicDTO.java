package br.com.paramazon.demo.application.dto;

import br.com.paramazon.demo.domain.enums.MusicalStyle;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Music", value = "Music", description = "Nomes de musicas")
public class MusicDTO implements Serializable {

    private Long idMusic;
    private String name;
    private MusicalStyle style;

}
