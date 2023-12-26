package br.com.paramazon.demo.application.controller;

import br.com.paramazon.demo.application.dto.show.presentation.PresentationDTO;
import br.com.paramazon.demo.application.services.show.presentation.PresentationService;
import br.com.paramazon.demo.infrastructure.response.shows.presentation.PresentationResponse;
import br.com.paramazon.demo.infrastructure.response.users.UserResponse;
import io.swagger.annotations.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/presentation")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(value = "/api/v1/presentation", produces = MediaType.APPLICATION_JSON_VALUE,
        tags = "Paramazon Gerenciamento de Apresentacoes",
        description = "Reúne endpoints destinados a lidar com o gerenciamento dos apresentacoes do Sistema")
public class PresentationController {

    private final PresentationService service;

    @SneakyThrows
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera todos as Bands cadastradas no sistema", response = UserResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de bandas conforme o padrão do Objeto abaixo", response = PresentationDTO.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado nenhuma banda na base"/*, response = NotFound404001Response.class*/)
    })
    public ResponseEntity<?> obterTodosApresentacoes() {
        log.info("PresentationController :: Iniciando o processo de obtenção de todas apresentacoes cadastradas no sistema!");
        var response = service.getAllBands();
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @GetMapping(value = "/{idPresentation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera um apresentacao atraves do id", response = UserResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna Presentation da base conforme o padrão do Objeto abaixo", response = PresentationResponse.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado a apresentacao solicitada"/*, response = NotFound404002Response.class*/)
    })
    public ResponseEntity<?> obterApresentacaoPorId(@PathVariable(name = "idPresentation") Long idPresentation) {
        log.info("PresentationController :: Iniciando o processo de obtenção de apresentacao de idPresentation = {}", idPresentation);
        var response = service.getBandById(idPresentation);
        return ResponseEntity.status(response.code()).body(response);
    }
}
