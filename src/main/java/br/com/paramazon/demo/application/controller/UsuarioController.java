package br.com.paramazon.demo.application.controller;

import br.com.paramazon.demo.application.dto.usuario.UsuarioLogadoDTO;
import br.com.paramazon.demo.application.services.UsuarioService;
import br.com.paramazon.demo.infrastructure.response.usuario.UsuarioResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
/*@Secured({ "ROLE_ADMIN" })*/
@Api(value = "/api/v1/usuario",
        produces = MediaType.APPLICATION_JSON_VALUE,
        tags = "Paramazon Gerenciamento de Usuarios",
        description = "Reúne endpoints destinados a lidar com o gerenciamento dos Usuarios do Sistema")
public class UsuarioController {

    private final UsuarioService service;

    @SneakyThrows
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
   @ApiOperation(value = "Recupera todos os Usuarios cadastrados no sistema", response = UsuarioResponse.class, httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de usuarios conforme o padrão do Objeto abaixo", response = UsuarioLogadoDTO.class),
            @ApiResponse(code = 404, message = "Retorna uma mensagem de erro quando não forem encontrados nenhum usuario na base"/*, response = NotFound404001Response.class*/)
    })
    public ResponseEntity<?> obterTodosUsuarios() {
        log.info("UsuarioController :: Iniciando o processo de obtenção de todos os usuarios cadastrados no sistema!");
        var response = service.getAllUsers();
        return ResponseEntity.status(response.getCode()).body(response);
    }

}