package br.com.paramazon.demo.application.services.show;

import br.com.paramazon.demo.application.dto.show.ShowVoteDTO;
import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.Show;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import br.com.paramazon.demo.domain.model.show.showVote.ShowVote;
import br.com.paramazon.demo.domain.model.show.showVote.presentationVote.PresentationVote;
import br.com.paramazon.demo.domain.repository.show.ShowRepository;
import br.com.paramazon.demo.domain.repository.show.presentation.PresentationRepository;
import br.com.paramazon.demo.domain.repository.show.showVote.ShowVoteRepository;
import br.com.paramazon.demo.domain.repository.show.showVote.presentationVote.PresentationVoteRepository;
import br.com.paramazon.demo.infrastructure.request.shows.*;
import br.com.paramazon.demo.infrastructure.response.shows.ShowResponse;
import br.com.paramazon.demo.infrastructure.response.shows.showVote.ShowVoteResponse;
import br.com.paramazon.demo.utils.show.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository repository;
    private final ShowVoteRepository voteRepository;

    private final PresentationRepository presentationRepository;
    private final PresentationVoteRepository presentationVoteRepository;

    private final static boolean IS_SHOW_RESPONSE = true;

    public ShowResponse getAllShows() {
        log.info("ShowService :: Obtendo todos os shows cadastrados no sistema...");
        List<Show> activeShows = repository.findAllByStatus(Status.ACTIVE);

        if (activeShows.isEmpty()) {
            log.info("ShowService :: Nenhum show encontrado!");
            return returnsError404NotFoundResponse("Não foi encontrado nenhum show na base", null, IS_SHOW_RESPONSE);
        }

        return new ShowResponse( HttpStatus.OK.value(),"Segue a lista com todos os shows encontrados",ShowUtils.buildBaseShowList(activeShows));
    }
    public ShowResponse getShowById(Long idShow) {
        log.info("ShowService :: Iniciando a busca de show...");
        Optional<Show> show = repository.findByIdShowAndStatus(idShow, Status.ACTIVE);

        if(show.isPresent()) {
            log.info("ShowService :: Show encontrado!");
            return new ShowResponse(HttpStatus.OK.value(), String.format("Segue os dados do show de id %d", idShow), ShowUtils.convertToDTO(show.get())
            );
        }

        return returnsError404NotFoundResponse(String.format("Show de ID %d não existe.", idShow), null, IS_SHOW_RESPONSE);
    }

    public ShowResponse createShow(RegisterShowRequest request) {
        log.info("ShowService :: Iniciando etapa de persistencia de um novo show...");
        try {
            log.info("ShowService :: Buscando apresentacoes na base...");
            List<Presentation> presentationList = presentationRepository.findAllById(request.idPresentationList());

            log.info("ShowService :: Verificando se ja existe algum Show agendado para {}...", request.date());
            if(repository.existsShowByDate(request.date()))
                return new ShowResponse(HttpStatus.CONFLICT.value(), "Já existe um show para a data "+ request.date()+"", null);

            log.info("ShowService :: Verificando se a data informada eh valida...", request.date());
            if(request.date().isBefore(LocalDate.now()))
                return returnsError400BadRequestResponse("A data informada (" + request.date() + ") não pode ser anterior a " + LocalDate.now(), IS_SHOW_RESPONSE);

            log.info("ShowService :: Salvando Show na base de dados...");
            Show savedShow = repository.save(ShowUtils.makeShowToPersist(request, presentationList));

            return new ShowResponse(HttpStatus.CREATED.value(), "Show criado com sucesso! (Data: "+ savedShow.getDate() +")", null);
        } catch(Exception error) {
            return returnsError500InternalServerErrorResponse(error, IS_SHOW_RESPONSE);
        }
    }

    public ShowResponse disableShow(Long idShow) {
        log.info("ShowService :: Iniciando etapa de desativação de show");
        Optional<Show> show = repository.findByIdShowAndStatus(idShow, Status.ACTIVE);
        try {
            if (show.isEmpty())
                return new ShowResponse(HttpStatus.NOT_FOUND.value(), "Show não encontrado!", null);

            log.info("ShowService :: Show encontrado!");
            Show showToBeDeleted = show.get();
            log.info("ShowService :: Desativando show...");
            showToBeDeleted.setStatus(Status.INACTIVE);
            repository.save(showToBeDeleted);
            log.info("ShowService :: Show desativado com sucesso!");
            return new ShowResponse(HttpStatus.NO_CONTENT.value(), "Show desativado com sucesso!", "");
        } catch (Exception e) {
            return returnsError500InternalServerErrorResponse(e, IS_SHOW_RESPONSE);
        }
    }

    /* ETAPA DE VOTACAO */
    public ShowVoteResponse getAllShowVotes() {
        log.info("ShowService :: Obtendo todos os shows cadastrados no sistema...");
        List<ShowVote> activeShowVotes = voteRepository.findAllByStatus(Status.ACTIVE);

        if (activeShowVotes.isEmpty()) {
            log.info("ShowService :: Nenhum show encontrado!");
            return returnsError404NotFoundResponse("Não foi encontrado nenhum show na base", null, !IS_SHOW_RESPONSE);
        }

        return new ShowVoteResponse(HttpStatus.OK.value(),"Segue a lista com todos os shows encontrados", ShowVoteUtils.buildBaseShowVoteList(activeShowVotes));
    }

    public ShowVoteResponse getShowVoteById(Long idShowVote) {
        log.info("ShowService :: Iniciando a busca de show...");
        Optional<ShowVote> showVote = voteRepository.findByIdShowVoteAndStatus(idShowVote, Status.ACTIVE);

        if(showVote.isPresent()) {
            log.info("ShowService :: Show encontrado!");
            return new ShowVoteResponse(HttpStatus.OK.value(),String.format("Segue os dados do show de id %d", idShowVote),ShowVoteUtils.convertToDTO(showVote.get()));
        }

        return returnsError404NotFoundResponse(String.format("Votação de show de ID %d não existe.", idShowVote), null, !IS_SHOW_RESPONSE);
    }

    public ShowVoteResponse createShowVote(RegisterShowVoteRequest request) {
        log.info("ShowService :: Iniciando etapa de persistencia de um novo show vote...");
        try {
            log.info("ShowService :: Verificando se as apresentacoes de ID {} existem na base...", request.idPresentationVoteList());
            List<PresentationVote> presentations = presentationVoteRepository.findAllById(request.idPresentationVoteList());

            if(presentations.isEmpty())
                return returnsError404NotFoundResponse("Nenhuma votacao em aberto.", null, !IS_SHOW_RESPONSE);

            log.info("ShowService :: Iniciando processo de salvamento...");
            ShowVote savedVoting = voteRepository.save(ShowVoteUtils.makeShowVoteToPersist(request, presentations));

            return new ShowVoteResponse(HttpStatus.CREATED.value(), "Votacao criada com sucesso!", ShowVoteUtils.convertToDTO(savedVoting));
        } catch(Exception error) {
            return returnsError500InternalServerErrorResponse(error, !IS_SHOW_RESPONSE);
        }
    }

    public ShowVoteResponse disableShowVote(Long idShowVote) {
        log.info("ShowService :: Iniciando etapa de desativação da votacao");
        Optional<ShowVote> showVote = voteRepository.findByIdShowVoteAndStatus(idShowVote, Status.ACTIVE);
        try {
            if (showVote.isEmpty())
                return new ShowVoteResponse(HttpStatus.NOT_FOUND.value(), "Votação de show não encontrada!", null);

            log.info("ShowService :: Show encontrado!");
            ShowVote showVoteToBeDeleted = showVote.get();
            log.info("ShowService :: Desativando show...");
            showVoteToBeDeleted.setStatus(Status.INACTIVE);
            voteRepository.save(showVoteToBeDeleted);
            log.info("ShowService :: Show desativado com sucesso!");
            return new ShowVoteResponse(HttpStatus.NO_CONTENT.value(), "Show desativado com sucesso!", "");
        } catch (Exception e) {
            return returnsError500InternalServerErrorResponse(e, !IS_SHOW_RESPONSE);
        }
    }

    /* METODOS PRIVADOS PARA AUXILIAR A CLASSE DE SERVICO */
    private <T> T returnsError404NotFoundResponse(String message, Object responseBody, boolean isShowResponse) {
        log.info("ShoweService :: Não foi possivel encontrar nenhum show!");
        int code = HttpStatus.NOT_FOUND.value();

        if(isShowResponse) return (T) new ShowResponse(code, message, responseBody);
        else return (T) new ShowVoteResponse(code, message, responseBody);
    }

    private <T> T returnsError500InternalServerErrorResponse(Exception error, boolean isShowResponse) {
        log.error(error.getLocalizedMessage());
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String messageError = "Ocorreu um erro desconhecido!";

        if(isShowResponse) return (T) new ShowResponse(code, messageError, error);
        else return (T) new ShowVoteResponse(code, messageError, error);
    }

    private <T> T  returnsError400BadRequestResponse(String message, boolean isShowResponse) {
        log.info(message);
        int code = HttpStatus.BAD_REQUEST.value();

        if(isShowResponse) return (T) new ShowResponse(code, message, null);
        else return (T) new ShowVoteResponse(code, message, null);
    }
}
