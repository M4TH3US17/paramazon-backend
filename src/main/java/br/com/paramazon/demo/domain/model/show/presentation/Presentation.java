package br.com.paramazon.demo.domain.model.show.presentation;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.media.Media;

import java.io.Serializable;
import java.time.LocalTime;

public class Presentation implements Serializable {

    private Long idPresentation;
    private Status status;
    private Band band;
    private Media photograph;
    private LocalTime startPresentation;
    private LocalTime endPresentation;
    private String description;
}
