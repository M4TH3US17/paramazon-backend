package br.com.paramazon.demo.application.services.show.presentation;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import br.com.paramazon.demo.domain.repository.show.presentation.PresentationRepository;
import br.com.paramazon.demo.infrastructure.response.shows.presentation.PresentationResponse;
import br.com.paramazon.demo.utils.show.presentation.PresentationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PresentationService {

    private final PresentationRepository repository;

    public PresentationResponse getAllPresentations() {
        log.info("PresentationService :: Obtendo todas as apresentacoes cadastrados no sistema...");
        List<Presentation> activePresentations = repository.findAllByStatus(Status.ACTIVE);

        if (activePresentations.isEmpty()) {
            log.info("PresentationService :: Nenhuma apresentacao encontrada!");
            return new PresentationResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Não foi encontrado nenhuma apresentacao na base",
                    new ArrayList<>());
        }

        return new PresentationResponse(
                HttpStatus.OK.value(),
                "Segue a lista de apresentacoes encontradas.",
                PresentationUtils.buildBasePresentationList(activePresentations));
    }

    public PresentationResponse getPresentationById(Long idPresentation) {
        log.info("PresentationService :: Obtendo apresentacao por id...");
        Optional<Presentation> presentation = repository.findByIdPresentationAndStatus(idPresentation, Status.ACTIVE);

        if(presentation.isPresent()) {
            log.info("PresentationService :: Apresentacao de id {} localizada com sucesso!", idPresentation);
            return new PresentationResponse(
                    HttpStatus.OK.value(),
                    String.format("Segue os dados da apresentacao de id %d", idPresentation),
                    PresentationUtils.convertToDTO(presentation.get()));
        }

        return new PresentationResponse(
                HttpStatus.NOT_FOUND.value(),
                String.format("Não foi encontrado nenhuma apresentacao de id %d na base", idPresentation),
                null);
    }

    public PresentationResponse disablePresentation(Long idPresentation) {
        log.info("PresentationService :: Iniciando etapa de desativação de presentation");
        Optional<Presentation> presentation = repository.findByIdPresentationAndStatus(idPresentation, Status.ACTIVE);
        try {
            if (presentation.isEmpty())
                return new PresentationResponse(HttpStatus.NOT_FOUND.value(), "Presentation nao encontrado!", null);

            log.info("PresentationService :: Presentation encontrada!");
            Presentation presentationToBeDeleted = presentation.get();
            log.info("PresentationService :: Desativando presentation...");
            presentationToBeDeleted.setStatus(Status.INACTIVE);
            repository.save(presentationToBeDeleted);
            log.info("PresentationService :: Presentation desativada com sucesso!");
            return new PresentationResponse(HttpStatus.NO_CONTENT.value(), "Presentation desativado com sucesso!", "");
        } catch (Exception e) {
            return /*setaInternalServerErroResponse(e)*/ null;
        }
    }
}
