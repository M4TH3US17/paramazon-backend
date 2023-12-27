package br.com.paramazon.demo.application.services.show.showVote.presentationVote;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import br.com.paramazon.demo.domain.model.show.showVote.presentationVote.PresentationVote;
import br.com.paramazon.demo.domain.repository.show.presentation.PresentationRepository;
import br.com.paramazon.demo.domain.repository.show.showVote.presentationVote.PresentationVoteRepository;
import br.com.paramazon.demo.infrastructure.response.shows.presentation.PresentationResponse;
import br.com.paramazon.demo.infrastructure.response.shows.showVote.presentationVote.PresentationVoteResponse;
import br.com.paramazon.demo.utils.show.presentation.PresentationUtils;
import br.com.paramazon.demo.utils.show.showVote.presentationVote.PresentationVoteUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PresentationVoteService {

    private final PresentationVoteRepository repository;

    public PresentationVoteResponse getAllPresentationVotes() {
        log.info("PresentationVoteService :: Obtendo todas as apresentacoes cadastrados no sistema (votacao)...");
        List<PresentationVote> activePresentationVotes = repository.findAllByStatus(Status.ACTIVE);

        if (activePresentationVotes.isEmpty()) {
            log.info("PresentationVoteService :: Nenhuma apresentacao encontrada! (votacao)");
            return returnsError404NotFoundResponse("Não há apresentações disponíveis para votação no momento.", new ArrayList<>());
        }

        return new PresentationVoteResponse(
                HttpStatus.OK.value(),
                "Lista de apresentações disponíveis para votação encontradas.",
                PresentationVoteUtils.buildBasePresentationVoteList(activePresentationVotes));
    }

    public PresentationVoteResponse getPresentationVoteById(Long idPresentationVote) {
        log.info("PresentationVoteService :: Obtendo apresentacao por id (votacao)...");
        Optional<PresentationVote> presentationVote = repository.findByIdPresentationVoteAndStatus(idPresentationVote, Status.ACTIVE);

        if(presentationVote.isPresent()) {
            log.info("PresentationVoteService :: Apresentacao de id {} localizada com sucesso! (votacao)", idPresentationVote);
            return new PresentationVoteResponse(
                    HttpStatus.OK.value(),
                    String.format("Dados da apresentação de ID %d disponíveis para votação.", idPresentationVote),
                    PresentationVoteUtils.convertToDTO(presentationVote.get()));
        }

        return returnsError404NotFoundResponse(String.format("Não foi encontrada nenhuma apresentação para votação com o ID %d na base de dados.", idPresentationVote), null);
    }

    public PresentationVoteResponse disablePresentationVote(Long idPresentationVote) {
        log.info("PresentationVoteService :: Iniciando etapa de desativação de presentation. (votacao)");
        Optional<PresentationVote> presentationVote = repository.findByIdPresentationVoteAndStatus(idPresentationVote, Status.ACTIVE);
        try {
            if (presentationVote.isEmpty())
                return returnsError404NotFoundResponse(String.format("A apresentação para votação de ID %d não foi encontrada.", idPresentationVote), null);

            log.info("PresentationVoteService :: Presentation de votacao encontrada!");
            PresentationVote presentationVoteToBeDeleted = presentationVote.get();
            log.info("PresentationVoteService :: Desativando presentation...");
            presentationVoteToBeDeleted.setStatus(Status.INACTIVE);
            repository.save(presentationVoteToBeDeleted);
            log.info("PresentationVoteService :: Presentation desativada com sucesso!");
            return new PresentationVoteResponse(HttpStatus.NO_CONTENT.value(), "Presentation desativada com sucesso!", "");
        } catch (Exception e) {
            return returnsError500InternalServerErrorResponse(e);
        }
    }

    /* METODOS PRIVADOS PARA AUXILIAR A CLASSE DE SERVICO */
    private PresentationVoteResponse returnsError404NotFoundResponse(String message, Object aFalse) {
        log.info("PresentationVoteService :: Não foi possivel encontrar nenhuma apresentacao!");
        return new PresentationVoteResponse(
                HttpStatus.NOT_FOUND.value(),
                message,
                Objects.nonNull(aFalse) ? aFalse : null);
    }

    private PresentationVoteResponse returnsError500InternalServerErrorResponse(Exception error) {
        log.error(error.getLocalizedMessage());
        return new PresentationVoteResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro desconhecido!",
                error);
    }

    private PresentationVoteResponse returnsError400BadRequestResponse(String message) {
        log.info(message);
        return new PresentationVoteResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                null);
    }


}
