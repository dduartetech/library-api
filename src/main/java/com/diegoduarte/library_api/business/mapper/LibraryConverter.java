package com.diegoduarte.library_api.business.mapper;

import com.diegoduarte.library_api.business.dto.AutoresDTO;
import com.diegoduarte.library_api.business.dto.ObrasDTO;
import com.diegoduarte.library_api.infrastructure.entity.AutoresEntity;
import com.diegoduarte.library_api.infrastructure.entity.ObrasEntity;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Builder
public class LibraryConverter {

   public static AutoresEntity paraAutorEntity(AutoresDTO dto) {
        if (dto == null) return null;
       return AutoresEntity.builder()
               .id(dto.getId())
               .nome(dto.getNome())
               .sexo(dto.getSexo())
               .email(dto.getEmail())
               .dataNascimento(dto.getDataNascimento())
               .paisOrigem(dto.getPaisOrigem())
               .cpf(dto.getCpf())
               .build();
    }

    public static AutoresDTO paraAutorDTO(AutoresEntity entity) {

        if (entity == null) return null;

        return AutoresDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .sexo(entity.getSexo())
                .email(entity.getEmail())
                .dataNascimento(entity.getDataNascimento())
                .paisOrigem(entity.getPaisOrigem())
                .cpf(entity.getCpf())
                .obrasIds(
                        entity.getObras() != null
                                ? entity.getObras()
                                .stream()
                                .map(obra -> obra.getId())
                                .toList()
                                : List.of()
                )
                .build();
    }

    public static ObrasEntity paraObraEntity(ObrasDTO dto) {
        if (dto == null) return null;
        return ObrasEntity.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .dataPublicacao(dto.getDataPublicacao())
                .dataExposicao(dto.getDataExposicao())
                .build();
    }

    public static ObrasDTO paraObraDTO(ObrasEntity entity) {
        if (entity == null) return null;
        return ObrasDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .dataPublicacao(entity.getDataPublicacao())
                .dataExposicao(entity.getDataExposicao())
                .autoresEmails(
                        entity.getAutores()
                                .stream()
                                .map(AutoresEntity::getEmail)
                                .toList()
                )
                .build();
    }


    public static List<ObrasDTO> paraListaObrasDTO(List<ObrasEntity> obras) {
        return obras.stream()
                .map(LibraryConverter::paraObraDTO)
                .toList();
    }

    public static List<AutoresDTO> paraListaAutorDTO(List<AutoresEntity> autores) {
        return autores.stream()
                .map(LibraryConverter::paraAutorDTO)
                .toList();
    }
}