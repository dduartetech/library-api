package com.diegoduarte.library_api.business;

import com.diegoduarte.library_api.business.dto.ObrasDTO;
import com.diegoduarte.library_api.business.mapper.LibraryConverter;

import com.diegoduarte.library_api.infrastructure.entity.AutoresEntity;
import com.diegoduarte.library_api.infrastructure.entity.ObrasEntity;
import com.diegoduarte.library_api.infrastructure.exceptions.ConflictException;
import com.diegoduarte.library_api.infrastructure.exceptions.ResourceNotFoundException;
import com.diegoduarte.library_api.infrastructure.repository.AutoresRepository;
import com.diegoduarte.library_api.infrastructure.repository.ObrasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObrasService {

    private final ObrasRepository obrasRepository;
    private final AutoresRepository autoresRepository;
    private final LibraryConverter libraryConverter;

    public ObrasDTO salvarObra (ObrasDTO obrasDTO) {
        if (obrasDTO.getDataExposicao() == null && obrasDTO.getDataPublicacao() == null) {
            throw new ConflictException("A obra precisa de pelo menos uma data (Publicação ou Exposição): ");
        }

        if (obrasDTO.getDescricao() != null && obrasDTO.getDescricao().length() > 240) {
            throw new ConflictException("Limite máximo de caracteres: 240.");
        }

        if (obrasDTO.getAutoresEmails() == null || obrasDTO.getAutoresEmails().isEmpty()) {
            throw new ConflictException("A obra precisa estar vinculada á pelo menos um autor.");
        }

        List<AutoresEntity> autoresEncontrados = obrasDTO.getAutoresEmails().stream().
                map(email -> autoresRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("Email não cadastrado.")))
                .collect(Collectors.toList());

        ObrasEntity obrasEntity = libraryConverter.paraObraEntity(obrasDTO);
        obrasEntity.setAutores(autoresEncontrados);
        return libraryConverter.paraObraDTO(obrasRepository.save(obrasEntity));
    }

    public ObrasDTO buscaObraPorTitulo (String titulo) {
        try{
            return libraryConverter.paraObraDTO(obrasRepository.findByNomeIgnoreCase(titulo).
                    orElseThrow(() -> new ResourceNotFoundException("Obra não encontrada.")));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Obra não encontrada.",e.getCause());
        }
    }

    public List<ObrasDTO> listarTodasAsObras() {
        List<ObrasEntity> obras = obrasRepository.findAll();
        return libraryConverter.paraListaObrasDTO(obras);
    }

    public void deletaObra (Long id) {
        obrasRepository.deleteById(id);
    }

}