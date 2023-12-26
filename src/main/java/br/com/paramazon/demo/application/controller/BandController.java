package br.com.paramazon.demo.application.controller;

import br.com.paramazon.demo.application.dto.show.band.BandDTO;
import br.com.paramazon.demo.application.services.show.band.BandService;
import br.com.paramazon.demo.infrastructure.response.shows.band.BandResponse;
import br.com.paramazon.demo.infrastructure.response.users.UserResponse;
import io.swagger.annotations.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/band")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(value = "/api/v1/band", produces = MediaType.APPLICATION_JSON_VALUE,
        tags = "Paramazon Gerenciamento de Bandas",
        description = "Reúne endpoints destinados a lidar com o gerenciamento dos Bandas do Sistema")
public class BandController {

    private final BandService service;

    @SneakyThrows
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera todos as Bands cadastradas no sistema", response = UserResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de bandas conforme o padrão do Objeto abaixo", response = BandDTO.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado nenhuma banda na base"/*, response = NotFound404001Response.class*/)
    })
    public ResponseEntity<?> obterTodosBandas() {
        log.info("BandController :: Iniciando o processo de obtenção de todas bandas cadastradas no sistema!");
        var response = service.getAllBands();
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @GetMapping(value = "/{idBand}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera um banda atraves do id", response = UserResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna Band da base conforme o padrão do Objeto abaixo", response = BandResponse.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado a banda solicitada"/*, response = NotFound404002Response.class*/)
    })
    public ResponseEntity<?> obterBandaPorId(@PathVariable(name = "idBand") Long idBand) {
        log.info("BandController :: Iniciando o processo de obtenção de banda de idBand = {}", idBand);
        var response = service.getBandById(idBand);
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @DeleteMapping(value = "/delete/{idBand}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta/desativa banda cadastrada."/*, response = LoginResponse.class*/, httpMethod = "DELETE", code = 204)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Retorna caso a banda tenha sido desativado com sucesso!", response = BandResponse.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrada a banda solicitado"/*, response = NotFound404002Response.class*/),
            @ApiResponse(code = 500, message = "Retorna uma mensagem de erro algum erro não identificado ocorrer."/*, response = InternalServer500000Response.class*/)
    })
    public ResponseEntity<?> desativarBanda(@PathVariable(name = "idBand") Long idBand) {
        log.info("BandController :: Iniciando o processo de desativação da banda de idBand {}", idBand);
        var response = service.disableBand(idBand);
        return ResponseEntity.status(response.code()).body(response);
    }
}
