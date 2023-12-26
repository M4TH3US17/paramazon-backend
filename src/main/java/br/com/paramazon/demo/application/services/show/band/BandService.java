package br.com.paramazon.demo.application.services.show.band;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.band.Band;
import br.com.paramazon.demo.domain.repository.show.band.BandRepository;
import br.com.paramazon.demo.infrastructure.response.shows.band.BandResponse;
import br.com.paramazon.demo.infrastructure.response.users.UserResponse;
import br.com.paramazon.demo.utils.show.band.BandUtils;
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
        Optional<Band> band = repository.findById(idBand);
        boolean isActive = Objects.equals(band.get().getStatus(), Status.ACTIVE);

        if(band.isPresent() && isActive) {
            log.info("BandService :: Banda de id {} localizada com sucesso!", idBand);
            return new BandResponse(
                    HttpStatus.OK.value(),
                    String.format("Segue os dados da banda de id %d", idBand),
                    BandUtils.convertToDTO(band.get()));
        }

        return new BandResponse(
                HttpStatus.NOT_FOUND.value(),
                "Não foi encontrado nenhuma banda na base",
                null);
    }
}
