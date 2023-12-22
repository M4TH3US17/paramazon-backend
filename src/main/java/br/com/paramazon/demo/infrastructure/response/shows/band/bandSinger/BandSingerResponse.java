package br.com.paramazon.demo.infrastructure.response.shows.band.bandSinger;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BandSingerResponse implements Serializable {
}
