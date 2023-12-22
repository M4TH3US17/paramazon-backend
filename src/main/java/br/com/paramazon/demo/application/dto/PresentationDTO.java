package br.com.paramazon.demo.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Presentation", value = "Presentation", description = "Apresentacao do sistema para uso.")
public class PresentationDTO implements Serializable {

    private Long idPresentation;
    private BandDTO band;
    private MediaDTO photograph;
    private LocalTime startPresentation;
    private LocalTime endPresentation;
    private String description;

}
