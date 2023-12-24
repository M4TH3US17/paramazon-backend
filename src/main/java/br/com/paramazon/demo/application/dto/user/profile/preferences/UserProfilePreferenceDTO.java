package br.com.paramazon.demo.application.dto.user.profile.preferences;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "User Preference", value = "User Preference", description = "Bandas Preferidas")
public record UserProfilePreferenceDTO(
        Long idBand,
        String name,
        String photograph,
        Set<UserPofileBandSingerPreferenceDTO> members,
        String description) {
}
