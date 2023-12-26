package br.com.paramazon.demo.application.services.show.band;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.repository.show.band.BandRepository;
import br.com.paramazon.demo.infrastructure.response.shows.band.BandResponse;
import br.com.paramazon.demo.utils.show.band.BandUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BandService {

    private final BandRepository repository;

    public BandResponse getAllBands() {
        log.info("BandService :: Obtendo todas as bandas cadastrados no sistema...");
        List<Band> activeBands = repository.findAllByStatus(Status.ACTIVE);

        if (activeBands.isEmpty()) {
            log.info("BandService :: Nenhuma banda encontrada!");
            return new BandResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Não foi encontrado nenhuma banda na base",
                    new ArrayList<>());
        }

        return new BandResponse(
                HttpStatus.OK.value(),
                "Segue a lista de bandas encontradas.",
                BandUtils.buildBaseBandList(activeBands));
    }

    public BandResponse getBandById(Long idBand) {
        log.info("BandService :: Obtendo banda por id...");
        Optional<Band> band = repository.findByIdBandAndStatus(idBand, Status.ACTIVE);

        if(band.isPresent()) {
            log.info("BandService :: Banda de id {} localizada com sucesso!", idBand);
            return new BandResponse(
                    HttpStatus.OK.value(),
                    String.format("Segue os dados da banda de id %d", idBand),
                    BandUtils.convertToDTO(band.get()));
        }

        return returnsError404NotFoundResponse("Não foi encontrado nenhuma banda na base",null);
    }

    public BandResponse disableBand(Long idBand) {
        log.info("BandService :: Iniciando etapa de desativação da banda");
        Optional<Band> band = repository.findByIdBandAndStatus(idBand, Status.ACTIVE);
        try {
            if (band.isEmpty())
                return returnsError404NotFoundResponse("Banda nao encontrada!", null);

            log.info("BandService :: Banda encontrada!");
            Band bandToBeDeleted = band.get();
            log.info("BandService :: Desativando banda...");
            bandToBeDeleted.setStatus(Status.INACTIVE);
            repository.save(bandToBeDeleted);
            log.info("BandService :: Banda desativada com sucesso!");
            return new BandResponse(HttpStatus.NO_CONTENT.value(), "Banda desativada com sucesso!", "");
        } catch (Exception e) {
            return returnsError500InternalServerErrorResponse(e);
        }
    }

    /* METODOS PRIVADOS PARA AUXILIAR A CLASSE DE SERVICO */
    private BandResponse returnsError404NotFoundResponse(String message, Object aFalse) {
        log.info("Não foi possivel encontrar nenhuma banda!");
        return new BandResponse(
                HttpStatus.NOT_FOUND.value(),
                message,
                Objects.nonNull(aFalse) ? aFalse : null);
    }

    private BandResponse returnsError500InternalServerErrorResponse(Exception error) {
        log.error(error.getLocalizedMessage());
        return new BandResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro desconhecido!",
                error);
    }

    private BandResponse returnsError400BadRequestResponse(String message) {
        log.info(message);
        return new BandResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                null);
    }
}
