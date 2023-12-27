package br.com.paramazon.demo.application.services.show.showVote;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.Show;
import br.com.paramazon.demo.domain.model.show.showVote.ShowVote;
import br.com.paramazon.demo.domain.repository.show.showVote.ShowVoteRepository;
import br.com.paramazon.demo.infrastructure.response.shows.ShowResponse;
import br.com.paramazon.demo.infrastructure.response.shows.showVote.ShowVoteResponse;
import br.com.paramazon.demo.utils.show.ShowUtils;
import br.com.paramazon.demo.utils.show.showVote.ShowVoteUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowVoteService {

    private final ShowVoteRepository repository;

    public ShowVoteResponse getAllShowVotes() {
        log.info("ShowVoteService :: Obtendo todos os shows cadastrados no sistema...");
        List<ShowVote> activeShowVotes = repository.findAllByStatus(Status.ACTIVE);

        if (activeShowVotes.isEmpty()) {
            log.info("ShowVoteService :: Nenhum show encontrado!");
            return returnsError404NotFoundResponse("Não foi encontrado nenhum show na base", null);
        }

        return new ShowVoteResponse(
                HttpStatus.OK.value(),
                "Segue a lista com todos os shows encontrados",
                ShowVoteUtils.buildBaseShowVoteList(activeShowVotes));
    }
    public ShowVoteResponse getShowVoteById(Long idShowVote) {
        log.info("ShowVoteService :: Iniciando a busca de show...");
        Optional<ShowVote> showVote = repository.findByIdShowVoteAndStatus(idShowVote, Status.ACTIVE);

        if(showVote.isPresent()) {
            log.info("ShowVoteService :: Show encontrado!");
            return new ShowVoteResponse(
                    HttpStatus.OK.value(),
                    String.format("Segue os dados do show de id %d", idShowVote),
                    ShowVoteUtils.convertToDTO(showVote.get()));
        }

        return returnsError404NotFoundResponse("Show cujo id é %d não existe", null);
    }

    public ShowVoteResponse disableShowVote(Long idShowVote) {
        log.info("ShowVoteService :: Iniciando etapa de desativação da votacao");
        Optional<ShowVote> showVote = repository.findByIdShowVoteAndStatus(idShowVote, Status.ACTIVE);
        try {
            if (showVote.isEmpty())
                return new ShowVoteResponse(HttpStatus.NOT_FOUND.value(), "Show nao encontrado!", null);

            log.info("ShowVoteService :: Show encontrado!");
            ShowVote showVoteToBeDeleted = showVote.get();
            log.info("ShowVoteService :: Desativando show...");
            showVoteToBeDeleted.setStatus(Status.INACTIVE);
            repository.save(showVoteToBeDeleted);
            log.info("ShowVoteService :: Show desativado com sucesso!");
            return new ShowVoteResponse(HttpStatus.NO_CONTENT.value(), "Show desativado com sucesso!", "");
        } catch (Exception e) {
            return returnsError500InternalServerErrorResponse(e);
        }
    }

    /* METODOS PRIVADOS PARA AUXILIAR A CLASSE DE SERVICO */
    private ShowVoteResponse returnsError404NotFoundResponse(String message, Object aFalse) {
        log.info("ShowVoteService :: Não foi possivel encontrar o show!");
        return new ShowVoteResponse(
                HttpStatus.NOT_FOUND.value(),
                message,
                Objects.nonNull(aFalse) ? aFalse : null);
    }

    private ShowVoteResponse returnsError500InternalServerErrorResponse(Exception error) {
        log.error(error.getLocalizedMessage());
        return new ShowVoteResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro desconhecido!",
                error);
    }

    private ShowVoteResponse returnsError400BadRequestResponse(String message) {
        log.info(message);
        return new ShowVoteResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                null);
    }
}
