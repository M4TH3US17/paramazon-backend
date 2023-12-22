package br.com.paramazon.demo.domain.model.show.showVote.presentationVote;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;

import java.io.Serializable;

public class PresentationVote implements Serializable {

    private Long idPresentationVote;
    private Presentation votingPresentation;
    private Integer votes;
    private Status status;
}
