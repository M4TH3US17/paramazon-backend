package br.com.paramazon.demo.domain.model.show.band;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.media.Media;
import br.com.paramazon.demo.domain.model.music.Music;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Band implements Serializable {

    private Long idBand;
    private String name;
    private Media photograph;
    private Status status;
    private Double totalPayment;
    private String description;
    private LocalDate createDate;
    private Set<Music> playlist = new HashSet<>();

    /* FALTA IMPLEMENTA A RELACAO ENTRE BANDA E CANTORES (PK DUPLA COM +ATRIBUTOS)*/
}
