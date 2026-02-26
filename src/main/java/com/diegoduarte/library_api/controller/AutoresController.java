package com.diegoduarte.library_api.controller;

import com.diegoduarte.library_api.business.AutoresService;
import com.diegoduarte.library_api.business.dto.AutoresDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutoresController {

    private final AutoresService autoresService;

    @PostMapping
    @Operation(summary = "Salva Autor(a)", description = "Cadastra um novo autor")
    @ApiResponse(responseCode = "200", description = "Autor(a) cadastrado com sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<AutoresDTO> salvaAutor (@RequestBody AutoresDTO autoresDTO) {
        return ResponseEntity.ok(autoresService.salvaAutor(autoresDTO));
    }

    @GetMapping("/{email}")
    @Operation(summary = "Busca Autor(a)", description = "Busca autor por email")
    @ApiResponse(responseCode = "200", description = "Autor(a) encontrado")
    @ApiResponse(responseCode = "404", description = "Autor(a) não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<AutoresDTO> buscaAutorPorEmail (@PathVariable("email") String email) {
        return ResponseEntity.ok(autoresService.buscaAutorPorEmail(email));
    }

    @PutMapping("/{email}")
    @Operation(summary = "Atualiza Dados de Autor(a)", description = "Atualiza dados de Autor(a) por email")
    @ApiResponse(responseCode = "200", description = "Autor(a) atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Autor(a) não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<AutoresDTO> atualizarAutor (@RequestBody AutoresDTO autoresDTO,
                                                      @PathVariable("email") String email) {
        return ResponseEntity.ok(autoresService.atualizarAutor(email,autoresDTO));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Autor(a)", description = "Deleta Autor(a) por email")
    @ApiResponse(responseCode = "200", description = "Autor(a) deletada com sucesso!")
    @ApiResponse(responseCode = "404", description = "Email não localizado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaAutorViaEmail (@PathVariable("email") String email) {
        autoresService.deletaAutorViaEmail(email);
        return ResponseEntity.ok().build();
    }
}
