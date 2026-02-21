package com.diegoduarte.library_api.controller;

import com.diegoduarte.library_api.business.AutoresService;
import com.diegoduarte.library_api.business.dto.AutoresDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutoresController {

    private final AutoresService autoresService;

    @PostMapping
    public ResponseEntity<AutoresDTO> salvaAutor (@RequestBody AutoresDTO autoresDTO) {
        return ResponseEntity.ok(autoresService.salvaAutor(autoresDTO));
    }

    @GetMapping("/{email}")
    public ResponseEntity<AutoresDTO> buscaAutorPorEmail (@PathVariable("email") String email) {
        return ResponseEntity.ok(autoresService.buscaAutorPorEmail(email));
    }

    @PutMapping("/{email}")
    public ResponseEntity<AutoresDTO> atualizarAutor (@RequestBody AutoresDTO autoresDTO,
                                                      @PathVariable("email") String email) {
        return ResponseEntity.ok(autoresService.atualizarAutor(email,autoresDTO));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaAutorViaEmail (@PathVariable("email") String email) {
        autoresService.deletaAutorViaEmail(email);
        return ResponseEntity.ok().build();
    }
}
