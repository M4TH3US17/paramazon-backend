package br.com.paramazon.demo.application.dto.user.profile.preferences;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "User Singer Band Preference",
        value = "User Singer Band Preference",
        description = "Membros pertecentes as bandas preferidas do usuario.")
public record UserPofileBandSingerPreferenceDTO(
        String member,
        String photograph) {
}