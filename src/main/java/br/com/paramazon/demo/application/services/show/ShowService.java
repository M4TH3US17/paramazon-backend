package br.com.paramazon.demo.application.services.show;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.Show;
import br.com.paramazon.demo.domain.repository.show.ShowRepository;
import br.com.paramazon.demo.infrastructure.response.shows.ShowResponse;
import br.com.paramazon.demo.utils.show.ShowUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository repository;

    public ShowResponse getAllShows() {
        log.info("ShowService :: Obtendo todos os shows cadastrados no sistema...");
        List<Show> activeShows = repository.findAllByStatus(Status.ACTIVE);

        if (activeShows.isEmpty()) {
            log.info("ShowService :: Nenhum show encontrado!");
            return returnsError404NotFoundResponse("Não foi encontrado nenhum show na base", null);
        }

        return new ShowResponse(
                HttpStatus.OK.value(),
                "Segue a lista com todos os shows encontrados",
                ShowUtils.buildBaseShowList(activeShows));
    }
    public ShowResponse getShowById(Long idShow) {
        log.info("ShowService :: Iniciando a busca de show...");
        Optional<Show> show = repository.findByIdShowAndStatus(idShow, Status.ACTIVE);

        if(show.isPresent()) {
            log.info("ShowService :: Show encontrado!");
            return new ShowResponse(
                    HttpStatus.OK.value(),
                    String.format("Segue os dados do show de id %d", idShow),
                    ShowUtils.convertToDTO(show.get())
            );
        }

        return returnsError404NotFoundResponse("Show cujo id é %d não existe", null);
    }

    public ShowResponse disableShow(Long idShow) {
        log.info("ShowService :: Iniciando etapa de desativação de show");
        Optional<Show> show = repository.findByIdShowAndStatus(idShow, Status.ACTIVE);
        try {
            if (show.isEmpty())
                return new ShowResponse(HttpStatus.NOT_FOUND.value(), "Show nao encontrado!", null);

            log.info("ShowService :: Show encontrado!");
            Show showToBeDeleted = show.get();
            log.info("ShowService :: Desativando show...");
            showToBeDeleted.setStatus(Status.INACTIVE);
            repository.save(showToBeDeleted);
            log.info("ShowService :: Show desativado com sucesso!");
            return new ShowResponse(HttpStatus.NO_CONTENT.value(), "Show desativado com sucesso!", "");
        } catch (Exception e) {
            return returnsError500InternalServerErrorResponse(e);
        }
    }

    /* METODOS PRIVADOS PARA AUXILIAR A CLASSE DE SERVICO */
    private ShowResponse returnsError404NotFoundResponse(String message, Object aFalse) {
        log.info("Não foi possivel encontrar o show!");
        return new ShowResponse(
                HttpStatus.NOT_FOUND.value(),
                message,
                Objects.nonNull(aFalse) ? aFalse : null);
    }

    private ShowResponse returnsError500InternalServerErrorResponse(Exception error) {
        log.error(error.getLocalizedMessage());
        return new ShowResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro desconhecido!",
                error);
    }

    private ShowResponse returnsError400BadRequestResponse(String message) {
        log.info(message);
        return new ShowResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                null);
    }

}
