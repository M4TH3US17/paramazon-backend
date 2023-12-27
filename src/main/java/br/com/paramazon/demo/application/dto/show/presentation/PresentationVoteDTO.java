package br.com.paramazon.demo.application.dto.show.presentation;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.*;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "PresentationVote do sistema para uso.")
@JsonPropertyOrder({"idPresentationVote", "votes", "votingPresentation"})
public record PresentationVoteDTO(
        @ApiModelProperty(value = "ID da votação de apresentação", example = "1") Long idPresentationVote,
        @ApiModelProperty(value = "Apresentação relacionada à votação") PresentationDTO votingPresentation,
        @ApiModelProperty(value = "Número de votos recebidos", example = "10") Integer votes
) implements Serializable {

}
