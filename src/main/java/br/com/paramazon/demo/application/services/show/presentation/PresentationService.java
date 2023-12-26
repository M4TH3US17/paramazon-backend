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

    public PresentationResponse getAllBands() {
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

    public PresentationResponse getBandById(Long idPresentation) {
        log.info("PresentationService :: Obtendo apresentacao por id...");
        Optional<Presentation> presentation = repository.findById(idPresentation);
        boolean isActive = Objects.equals(presentation.get().getStatus(), Status.ACTIVE);

        if(presentation.isPresent() && isActive) {
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
}
