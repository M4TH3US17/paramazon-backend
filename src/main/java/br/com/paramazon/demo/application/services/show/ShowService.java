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
            return new ShowResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Não foi encontrado nenhum show na base",
                    null);
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
                    String.format("Segue os dados dados do show de id %d", idShow),
                    ShowUtils.convertToDTO(show.get())
            );
        }

        return new ShowResponse(
                HttpStatus.NOT_FOUND.value(),
                String.format("Show cujo id é %d não existe", idShow),
                null);
    }

}
