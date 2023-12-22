package br.com.paramazon.demo.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Show", value = "Show", description = "Show do sistema para uso.")
public class ShowDTO implements Serializable {

    private Long idShow;
    private LocalDate date;
    private Double budget;
    private List<PresentationDTO> presentations;

}
