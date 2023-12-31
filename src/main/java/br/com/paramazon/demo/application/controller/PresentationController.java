package br.com.paramazon.demo.application.controller;

import br.com.paramazon.demo.application.dto.show.presentation.PresentationDTO;
import br.com.paramazon.demo.application.dto.show.presentation.PresentationVoteDTO;
import br.com.paramazon.demo.application.services.show.presentation.PresentationService;
import br.com.paramazon.demo.domain.model.show.presentation.Presentation;
import br.com.paramazon.demo.infrastructure.request.shows.presentation.RegisterPresentationRequest;
import br.com.paramazon.demo.infrastructure.request.shows.presentation.RegisterPresentationVoteRequest;
import br.com.paramazon.demo.infrastructure.response.shows.presentation.PresentationResponse;
import br.com.paramazon.demo.infrastructure.response.shows.showVote.presentationVote.PresentationVoteResponse;
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
    @ApiOperation(value = "Recupera todos as Presentations cadastradas no sistema", response = PresentationResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de bandas conforme o padrão do Objeto abaixo", response = PresentationDTO.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado nenhuma banda na base"/*, response = NotFound404001Response.class*/)
    })
    public ResponseEntity<?> obterTodasApresentacoes() {
        log.info("PresentationController :: Iniciando o processo de obtenção de todas apresentacoes cadastradas no sistema!");
        var response = service.getAllPresentations();
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
        var response = service.getPresentationById(idPresentation);
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Solicita o cadastro de uma Presentation no sistema", response = PresentationResponse.class, httpMethod = "POST", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a presentation caso ja tenha sido cadastrado na base de dados, conforme padrão abaixo", response = PresentationDTO.class),
            @ApiResponse(code = 201, message = "Retorna a presentation cadastrada na base de dados, conforme padrão abaixo", response = PresentationDTO.class),
            @ApiResponse(code = 500, message = "Retorna uma mensagem de erro algum erro não identificado ocorrer."/*, response = InternalServer500000Response.class*/)
    })
    public ResponseEntity<?> createPresentation(@RequestBody RegisterPresentationRequest request) {
        log.info("PresentationController :: Iniciando o processo de persistencia de uma nova apresentacao...");
        var response = service.createPresentation(request);
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @DeleteMapping(value = "/delete/{idPresentation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta/desativa Presentation cadastrada."/*, response = LoginResponse.class*/, httpMethod = "DELETE", code = 204)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Retorna caso a apresentacao tenha sido desativado com sucesso!", response = PresentationResponse.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrada a apresentacao solicitada"/*, response = NotFound404002Response.class*/),
            @ApiResponse(code = 500, message = "Retorna uma mensagem de erro caso algum erro não identificado ocorrer."/*, response = InternalServer500000Response.class*/)
    })
    public ResponseEntity<?> desativarPresentation(@PathVariable(name = "idPresentation") Long idPresentation) {
        log.info("PresentationController :: Iniciando o processo de desativação da Presentation de idPresentation {}", idPresentation);
        var response = service.disablePresentation(idPresentation);
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @GetMapping(value = "/votes/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera todos as PresentationVote cadastradas no sistema", response = PresentationVoteResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de bandas conforme o padrão do Objeto abaixo", response = PresentationVoteDTO.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado nenhuma banda na base"/*, response = NotFound404001Response.class*/)
    })
    public ResponseEntity<?> obterTodasVotacoesDeApresentacoes() {
        log.info("PresentationController :: Iniciando o processo de obtenção de todas votacoes de apresentacoes cadastradas no sistema!");
        var response = service.getAllPresentationVotes();
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @GetMapping(value = "/votes/{idPresentationVote}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera uma votacao de apresentacao atraves do id", response = PresentationVoteResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna Presentation da base conforme o padrão do Objeto abaixo", response = PresentationVoteResponse.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrado a apresentacao solicitada"/*, response = NotFound404002Response.class*/)
    })
    public ResponseEntity<?> obterVotacaoDeApresentacaoPorId(@PathVariable(name = "idPresentationVote") Long idPresentationVote) {
        log.info("PresentationController :: Iniciando o processo de obtenção de votacao de apresentacao de idPresentationVote = {}", idPresentationVote);
        var response = service.getPresentationVoteById(idPresentationVote);
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @PostMapping(value = "/votes/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Solicita o cadastro de uma Presentation vote no sistema", response = PresentationVoteResponse.class, httpMethod = "POST", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a presentation vote caso ja tenha sido cadastrada na base de dados, conforme padrão abaixo", response = PresentationVoteDTO.class),
            @ApiResponse(code = 201, message = "Retorna a presentation vote cadastrada na base de dados, conforme padrão abaixo", response = PresentationVoteDTO.class),
            @ApiResponse(code = 500, message = "Retorna uma mensagem de erro algum erro não identificado ocorrer."/*, response = InternalServer500000Response.class*/)
    })
    public ResponseEntity<?> createPresentationVote(@RequestBody RegisterPresentationVoteRequest request) {
        log.info("PresentationController :: Iniciando o processo de persistencia de uma nova votacao de apresentacao...");
        var response = service.createPresentationVote(request);
        return ResponseEntity.status(response.code()).body(response);
    }

    @SneakyThrows
    @DeleteMapping(value = "/votes/delete/{idPresentationVote}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta/desativa Presentation Vote cadastrada."/*, response = LoginResponse.class*/, httpMethod = "DELETE", code = 204)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Retorna caso a apresentacao a ser votada tenha sido desativado com sucesso!", response = PresentationVoteResponse.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não for encontrada a apresentacao solicitada"/*, response = NotFound404002Response.class*/),
            @ApiResponse(code = 500, message = "Retorna uma mensagem de erro caso algum erro não identificado ocorrer."/*, response = InternalServer500000Response.class*/)
    })
    public ResponseEntity<?> desativarPresentationVote(@PathVariable(name = "idPresentationVote") Long idPresentationVote) {
        log.info("PresentationController :: Iniciando o processo de desativação da PresentationVote de idPresentationVote {}", idPresentationVote);
        var response = service.disablePresentationVote(idPresentationVote);
        return ResponseEntity.status(response.code()).body(response);
    }


}
