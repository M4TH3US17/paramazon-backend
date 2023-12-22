package br.com.paramazon.demo.domain.model.show;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Show implements Serializable {

    private Long idShow;
    private LocalDate date;
    private Status status;
    private Double budget;
    private List<Presentation> presentations = new ArrayList<>();
}
