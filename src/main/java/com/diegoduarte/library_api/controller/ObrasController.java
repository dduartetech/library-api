package com.diegoduarte.library_api.controller;

import com.diegoduarte.library_api.business.ObrasService;
import com.diegoduarte.library_api.business.dto.ObrasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObrasController {

    private final ObrasService obrasService;

    @PostMapping
    public ResponseEntity<ObrasDTO> salvaObra (@RequestBody ObrasDTO obrasDTO) {
        return ResponseEntity.ok(obrasService.salvarObra(obrasDTO));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ObrasDTO> buscaObraPorTitulo (@RequestParam("titulo") String titulo) {
        return ResponseEntity.ok(obrasService.buscaObraPorTitulo(titulo));
    }

    @GetMapping
    public ResponseEntity<List<ObrasDTO>> listarTodasAsObras () {
        return ResponseEntity.ok(obrasService.listarTodasAsObras());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaObra (@PathVariable("id") Long id) {
        obrasService.deletaObra(id);
        return ResponseEntity.ok().build();
    }

}
