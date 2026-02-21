package com.diegoduarte.library_api.business;

import com.diegoduarte.library_api.business.dto.AutoresDTO;
import com.diegoduarte.library_api.business.mapper.LibraryConverter;
import com.diegoduarte.library_api.infrastructure.entity.AutoresEntity;
import com.diegoduarte.library_api.infrastructure.exceptions.ConflictException;
import com.diegoduarte.library_api.infrastructure.exceptions.ResourceNotFoundException;
import com.diegoduarte.library_api.infrastructure.repository.AutoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutoresService {

    private final AutoresRepository autoresRepository;
    private final LibraryConverter libraryConverter;

    public boolean emailExiste (String email) {
        return autoresRepository.existsByEmail(email);
    }

    public void verificaEmail (String email) {
        try {
            boolean existe = emailExiste(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado: " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado: " + email, e.getCause());
        }
    }

    public AutoresDTO salvaAutor (AutoresDTO autoresDTO) {
        verificaEmail(autoresDTO.getEmail());
        if ("Brasil".equalsIgnoreCase(autoresDTO.getPaisOrigem()) &&
                (autoresDTO.getCpf() == null || autoresDTO.getCpf().isBlank())) {
            throw new ConflictException("Para autores brasileiros, o CPF é obrigatório!");
        }

        AutoresEntity autor = libraryConverter.paraAutorEntity(autoresDTO);
        autor = autoresRepository.save(autor);
        return libraryConverter.paraAutorDTO(autor);
    }

    public AutoresDTO buscaAutorPorEmail (String email) {
        AutoresEntity autor = autoresRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não cadastrado: " + email));

        return libraryConverter.paraAutorDTO(autor);
    }

    public AutoresDTO atualizarAutor(String email, AutoresDTO dtoNovo) {
        AutoresEntity autor = autoresRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));

        autor.setNome(dtoNovo.getNome());
        autor.setCpf(dtoNovo.getCpf());

        AutoresEntity autorAtualizado = autoresRepository.save(autor);
        return libraryConverter.paraAutorDTO(autorAtualizado);
    }

    public void deletaAutorViaEmail(String email) {
        autoresRepository.deleteByEmail(email);
    }

}
