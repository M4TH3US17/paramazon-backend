package br.com.paramazon.demo.infrastructure.request.shows;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        discriminator = "Register Show Request",
        value = "Register Show Request",
        description = "Entidade RegisterShowRequest para auxiliar a classe Show Vote.")
public record RegisterShowVoteRequest(
        LocalDateTime startVoting,
        LocalDateTime endVoting,
        List<Long> idPresentationVoteList
) {
}
