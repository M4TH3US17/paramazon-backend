package br.com.paramazon.demo.application.controller;

import br.com.paramazon.demo.application.dto.show.ShowDTO;
import br.com.paramazon.demo.application.services.show.ShowService;
import br.com.paramazon.demo.infrastructure.response.shows.ShowResponse;
import io.swagger.annotations.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/show")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Api(value = "/api/v1/show", produces = MediaType.APPLICATION_JSON_VALUE,
        tags = "Paramazon Gerenciamento de Shows",
        description = "Reúne endpoints destinados a lidar com o gerenciamento dos shows do Sistema")
public class ShowController {

    private final ShowService service;

    @SneakyThrows
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera todos os shows cadastradas no sistema", response = ShowResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de shows conforme o padrão do Objeto abaixo", response = ShowDTO.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado nenhuma show na base"/*, response = NotFound404001Response.class*/)
    })
    public ResponseEntity<?> obterTodosShows() {
        log.info("ShowController :: Iniciando o processo de obtenção de todas apresentacoes cadastradas no sistema!");
        var response = service.getAllShows();
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @GetMapping(value = "/{idShow}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera um show atraves do id", response = ShowResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna Presentation da base conforme o padrão do Objeto abaixo", response = ShowResponse.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado o show solicitada"/*, response = NotFound404002Response.class*/)
    })
    public ResponseEntity<?> obterShowPorId(@PathVariable(name = "idShow") Long idShow) {
        log.info("ShowController :: Iniciando o processo de obtenção de show de idShow = {}", idShow);
        var response = service.getShowById(idShow);
        return ResponseEntity.status(response.code()).body(response);
    }
}
