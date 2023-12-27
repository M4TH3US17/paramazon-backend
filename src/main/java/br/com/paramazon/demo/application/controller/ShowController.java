package br.com.paramazon.demo.application.controller;

import br.com.paramazon.demo.application.dto.show.ShowDTO;
import br.com.paramazon.demo.application.dto.show.ShowVoteDTO;
import br.com.paramazon.demo.application.services.show.ShowService;
import br.com.paramazon.demo.infrastructure.response.shows.ShowResponse;
import br.com.paramazon.demo.infrastructure.response.shows.showVote.ShowVoteResponse;
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

    @SneakyThrows
    @DeleteMapping(value = "/delete/{idShow}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta/desativa banda cadastrada."/*, response = LoginResponse.class*/, httpMethod = "DELETE", code = 204)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Retorna caso a show tenha sido desativado com sucesso!", response = ShowResponse.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrada o show solicitado"/*, response = NotFound404002Response.class*/),
            @ApiResponse(code = 500, message = "Retorna uma mensagem de erro caso algum erro não identificado ocorrer."/*, response = InternalServer500000Response.class*/)
    })
    public ResponseEntity<?> desativarShow(@PathVariable(name = "idShow") Long idShow) {
        log.info("ShowController :: Iniciando o processo de desativação da show de idShow {}", idShow);
        var response = service.disableShow(idShow);
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @GetMapping(value = "/show-votes/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera todos os shows cadastradas no sistema", response = ShowVoteResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de Show Votes conforme o padrão do Objeto abaixo", response = ShowVoteDTO.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado nenhuma Show Vote na base"/*, response = NotFound404001Response.class*/)
    })
    public ResponseEntity<?> obterTodosShowVotes() {
        log.info("ShowController :: Iniciando o processo de obtenção de todas votacoes cadastradas no sistema!");
        var response = service.getAllShowVotes();
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @GetMapping(value = "/show-votes/{idShowVote}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera um show vote atraves do id", response = ShowVoteResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna ShowVote da base conforme o padrão do Objeto abaixo", response = ShowVoteResponse.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado o Show Vote solicitada"/*, response = NotFound404002Response.class*/)
    })
    public ResponseEntity<?> obterShowVotePorId(@PathVariable(name = "idShowVote") Long idShowVote) {
        log.info("ShowController :: Iniciando o processo de obtenção de Show Vote de ID = {}", idShowVote);
        var response = service.getShowVoteById(idShowVote);
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @DeleteMapping(value = "/show-votes/delete/{idShowVote}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta/desativa Show Vote cadastrada."/*, response = LoginResponse.class*/, httpMethod = "DELETE", code = 204)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Retorna caso a Show Vote tenha sido desativado com sucesso!", response = ShowVoteResponse.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrada o Show Vote solicitado"/*, response = NotFound404002Response.class*/),
            @ApiResponse(code = 500, message = "Retorna uma mensagem de erro caso algum erro não identificado ocorrer."/*, response = InternalServer500000Response.class*/)
    })
    public ResponseEntity<?> desativarShowVote(@PathVariable(name = "idShowVote") Long idShowVote) {
        log.info("ShowController :: Iniciando o processo de desativação da Show Vote de idShowVote {}", idShowVote);
        var response = service.disableShowVote(idShowVote);
        return ResponseEntity.status(response.code()).body(response);
    }
}
