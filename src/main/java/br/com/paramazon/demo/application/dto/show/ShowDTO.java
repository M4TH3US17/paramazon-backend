package br.com.paramazon.demo.application.dto.show;

import br.com.paramazon.demo.application.dto.show.presentation.PresentationDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.*;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Show", value = "Show", description = "Show do sistema para uso.")
public record ShowDTO(
        Long idShow,
        LocalDate date,
        Double budget,
        List<PresentationDTO> presentations
) {}
