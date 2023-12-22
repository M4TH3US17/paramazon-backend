package br.com.paramazon.demo.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "Band", value = "Band", description = "Banda do sistema para uso.")
public class BandDTO implements Serializable {

    private Long idBand;
    private String name;
    private MediaDTO photograph;
    private Double totalPayment;
    private String description;
    private Set<MusicDTO> playlist;
    private Set<BandSingerDTO> bandSingers;

}
