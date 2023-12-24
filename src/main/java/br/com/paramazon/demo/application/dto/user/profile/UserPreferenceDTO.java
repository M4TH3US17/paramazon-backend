package br.com.paramazon.demo.application.dto.user.profile;

import br.com.paramazon.demo.application.dto.media.MediaDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "User Preference", value = "User Preference", description = "Bandas Preferidas")
public record UserPreferenceDTO(Long idBand, String name, MediaDTO photograph, String description) {
}
