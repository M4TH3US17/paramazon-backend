package br.com.paramazon.demo.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.*;

import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Presentation", value = "Presentation", description = "Apresentacao do sistema para uso.")
public record PresentationDTO(
        Long idPresentation,
        BandDTO band,
        MediaDTO photograph,
        LocalTime startPresentation,
        LocalTime endPresentation,
        String description) {

}