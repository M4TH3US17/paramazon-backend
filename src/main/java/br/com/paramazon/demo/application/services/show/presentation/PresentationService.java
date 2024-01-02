package br.com.paramazon.demo.application.services.show.presentation;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import br.com.paramazon.demo.domain.model.show.showVote.presentationVote.PresentationVote;
import br.com.paramazon.demo.domain.repository.show.band.BandRepository;
import br.com.paramazon.demo.domain.repository.show.presentation.PresentationRepository;
import br.com.paramazon.demo.domain.repository.show.showVote.presentationVote.PresentationVoteRepository;
import br.com.paramazon.demo.infrastructure.request.shows.presentation.*;
import br.com.paramazon.demo.infrastructure.response.shows.presentation.PresentationResponse;
import br.com.paramazon.demo.infrastructure.response.shows.showVote.presentationVote.PresentationVoteResponse;
import br.com.paramazon.demo.utils.show.presentation.*;
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
    private final PresentationVoteRepository voteRepository;

    private final BandRepository bandRepository;

    private final static boolean IS_PRESENTATION_RESPONSE = true;

    public PresentationResponse getAllPresentations() {
        log.info("PresentationService :: Obtendo todas as apresentacoes cadastrados no sistema...");
        List<Presentation> activePresentations = repository.findAllByStatus(Status.ACTIVE);

        if (activePresentations.isEmpty()) {
            log.info("PresentationService :: Nenhuma apresentacao encontrada!");
            return returnsError404NotFoundResponse("Não foi encontrado nenhuma apresentacao na base", new ArrayList<>(), IS_PRESENTATION_RESPONSE);
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
            return new PresentationResponse(HttpStatus.OK.value(), String.format("Segue os dados da apresentacao de id %d", idPresentation), PresentationUtils.convertToDTO(presentation.get()));
        }

        return returnsError404NotFoundResponse(String.format("Não foi encontrado nenhuma apresentacao de ID %d na base", idPresentation), null, IS_PRESENTATION_RESPONSE);
    }

    public PresentationResponse createPresentation(RegisterPresentationRequest request) {
        log.info("PresentationService :: Iniciando etapa de persistencia de uma nova apresentacao...");
        try {
            log.info("PresentationService :: Localizando na base Banda de ID {}...", request.idBand());
            Band presentationBand = bandRepository.findByIdBandAndStatus(request.idBand(), Status.ACTIVE).orElse(null);

            if(Objects.isNull(presentationBand)) {
                log.warn("PresentationService :: Banda de ID {} não encontrada!", request.idBand());
                return returnsError404NotFoundResponse(String.format("Banda ID %d não encontrada!", request.idBand()), null, IS_PRESENTATION_RESPONSE);
            }

            Presentation presentation = PresentationUtils.makePresentationToPersist(request, presentationBand/*, s3Service*/);
            log.info("PresentationService :: Salvando apresentacao...");
            Presentation savedPresentation = repository.save(presentation);

            return new PresentationResponse(HttpStatus.CREATED.value(),"Apresentação cadastrada com sucesso!", PresentationUtils.convertToDTO(savedPresentation));
        } catch (Exception e) {
            return returnsError500InternalServerErrorResponse(e, IS_PRESENTATION_RESPONSE);
        }
    }

    public PresentationResponse disablePresentation(Long idPresentation) {
        log.info("PresentationService :: Iniciando etapa de desativação de presentation");
        Optional<Presentation> presentation = repository.findByIdPresentationAndStatus(idPresentation, Status.ACTIVE);
        try {
            if (presentation.isEmpty())
                return returnsError404NotFoundResponse("Apresentação não encontrada!", null, IS_PRESENTATION_RESPONSE);

            log.info("PresentationService :: Presentation encontrada!");
            Presentation presentationToBeDeleted = presentation.get();
            log.info("PresentationService :: Desativando presentation...");
            presentationToBeDeleted.setStatus(Status.INACTIVE);
            repository.save(presentationToBeDeleted);
            log.info("PresentationService :: Presentation desativada com sucesso!");
            return new PresentationResponse(HttpStatus.NO_CONTENT.value(), "Presentation desativado com sucesso!", "");
        } catch (Exception e) {
            return returnsError500InternalServerErrorResponse(e, IS_PRESENTATION_RESPONSE);
        }
    }

    /* ETAPA DE VOTACAO */
    public PresentationVoteResponse getAllPresentationVotes() {
        log.info("PresentationService :: Obtendo todas as apresentacoes cadastrados no sistema (votacao)...");
        List<PresentationVote> activePresentationVotes = voteRepository.findAllByStatus(Status.ACTIVE);

        if (activePresentationVotes.isEmpty()) {
            log.info("PresentationService :: Nenhuma apresentacao encontrada! (votacao)");
            return returnsError404NotFoundResponse("Não há apresentações disponíveis para votação no momento.", new ArrayList<>(), !IS_PRESENTATION_RESPONSE);
        }

        return new PresentationVoteResponse(
                HttpStatus.OK.value(),
                "Lista de apresentações disponíveis para votação encontradas.",
                PresentationVoteUtils.buildBasePresentationVoteList(activePresentationVotes));
    }

    public PresentationVoteResponse getPresentationVoteById(Long idPresentationVote) {
        log.info("PresentationService :: Obtendo apresentacao por id (votacao)...");
        Optional<PresentationVote> presentationVote = voteRepository.findByIdPresentationVoteAndStatus(idPresentationVote, Status.ACTIVE);

        if(presentationVote.isPresent()) {
            log.info("PresentationService :: Apresentacao de id {} localizada com sucesso! (votacao)", idPresentationVote);
            return new PresentationVoteResponse(
                    HttpStatus.OK.value(),
                    String.format("Dados da apresentação de ID %d disponíveis para votação.", idPresentationVote),
                    PresentationVoteUtils.convertToDTO(presentationVote.get()));
        }

        return returnsError404NotFoundResponse(String.format("Não foi encontrada nenhuma apresentação para votação com o ID %d na base de dados.", idPresentationVote), null, !IS_PRESENTATION_RESPONSE);
    }

    public PresentationVoteResponse createPresentationVote(RegisterPresentationVoteRequest request) {
        log.info("PresentationService :: Iniciando etapa de persistencia de uma nova apresentacao para votacao...");
        try {
            log.info("PresentationService :: Buscando na base Presentation de ID {}...", request.idPresentation());
            Presentation presentation = repository.findByIdPresentationAndStatus(request.idPresentation(), Status.ACTIVE).orElse(null);

            if(Objects.isNull(presentation))
                return returnsError404NotFoundResponse("A apresentação de ID "+ request.idPresentation() +" não foi localizada.", null, !IS_PRESENTATION_RESPONSE);

            log.info("PresentationService :: Salvando apresentacao para ser votada...");
            PresentationVote savedVotePresentation = voteRepository.save(PresentationVoteUtils.makePresentationVoteToPersist(presentation));

            return new PresentationVoteResponse( HttpStatus.CREATED.value(), "Apresentação cadastrada com sucesso!", PresentationVoteUtils.convertToDTO(savedVotePresentation));

        } catch (Exception e) {
            return returnsError500InternalServerErrorResponse(e, !IS_PRESENTATION_RESPONSE);
        }
    }

    public PresentationVoteResponse disablePresentationVote (Long idPresentationVote){
                log.info("PresentationService :: Iniciando etapa de desativação de presentation. (votacao)");
                Optional<PresentationVote> presentationVote = voteRepository.findByIdPresentationVoteAndStatus(idPresentationVote, Status.ACTIVE);
                try {
                    if (presentationVote.isEmpty())
                        return returnsError404NotFoundResponse(String.format("A apresentação para votação de ID %d não foi encontrada.", idPresentationVote), null, !IS_PRESENTATION_RESPONSE);

                    log.info("PresentationService :: Presentation de votacao encontrada!");
                    PresentationVote presentationVoteToBeDeleted = presentationVote.get();
                    log.info("PresentationService :: Desativando presentation...");
                    presentationVoteToBeDeleted.setStatus(Status.INACTIVE);
                    voteRepository.save(presentationVoteToBeDeleted);
                    log.info("PresentationService :: Presentation desativada com sucesso!");
                    return new PresentationVoteResponse(HttpStatus.NO_CONTENT.value(), "Presentation desativada com sucesso!", "");
                } catch (Exception e) {
                    return returnsError500InternalServerErrorResponse(e, !IS_PRESENTATION_RESPONSE);
                }
            }

    /* METODOS PRIVADOS PARA AUXILIAR A CLASSE DE SERVICO */
    private <T> T returnsError404NotFoundResponse(String message, Object responseBody, boolean isPresentationResponse) {
        log.error("PresentationService :: Erro 404 (Not Found)!");
        int code = HttpStatus.NOT_FOUND.value();

        if(isPresentationResponse) return (T) new PresentationResponse(code, message, responseBody);
        else return (T) new PresentationVoteResponse(code, message, responseBody);
    }

    private <T> T returnsError500InternalServerErrorResponse(Exception error, boolean isPresentationResponse) {
        log.error(error.getLocalizedMessage());
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String messageError = "Ocorreu um erro desconhecido!";

        if(isPresentationResponse) return (T) new PresentationResponse(code, messageError, error);
        else return (T) new PresentationVoteResponse(code, messageError, error);
    }

    private <T> T  returnsError400BadRequestResponse(String message, boolean isPresentationResponse) {
        log.info(message);
        int code = HttpStatus.BAD_REQUEST.value();

        if(isPresentationResponse) return (T) new PresentationResponse(code, message, null);
        else return (T) new PresentationVoteResponse(code, message, null);
    }

}
