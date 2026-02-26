package com.diegoduarte.library_api.controller;

import com.diegoduarte.library_api.business.ObrasService;
import com.diegoduarte.library_api.business.dto.ObrasDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
@Tag(name = "Obras", description = "Cadastra obras vinculadas a autores")

public class ObrasController {

    private final ObrasService obrasService;

    @PostMapping
    @Operation(summary = "Salva Obras", description = "Cadastra uma nova obra")
    @ApiResponse(responseCode = "200", description = "Obra cadastrada com sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ObrasDTO> salvaObra (@RequestBody ObrasDTO obrasDTO) {
        return ResponseEntity.ok(obrasService.salvarObra(obrasDTO));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Busca Obras por Titulo", description = "Lista obras pelo seu titulo")
    @ApiResponse(responseCode = "200", description = "Obra(s) encontrada!")
    @ApiResponse(responseCode = "404", description = "Obra(s) não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ObrasDTO> buscaObraPorTitulo (@RequestParam("titulo") String titulo) {
        return ResponseEntity.ok(obrasService.buscaObraPorTitulo(titulo));
    }

    @GetMapping
    @Operation(summary = "Lista Todas as Obras", description = "Lista todas as obras cadastradas")
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso")
    @ApiResponse(responseCode = "404", description = "Nenhuma obra encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<ObrasDTO>> listarTodasAsObras () {
        return ResponseEntity.ok(obrasService.listarTodasAsObras());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Obra", description = "Deleta obra por id")
    @ApiResponse(responseCode = "200", description = "Obra deletada com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id não localizado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaObra (@PathVariable("id") Long id) {
        obrasService.deletaObra(id);
        return ResponseEntity.ok().build();
    }

}
