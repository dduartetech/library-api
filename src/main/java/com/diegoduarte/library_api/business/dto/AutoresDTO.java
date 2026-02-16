package com.diegoduarte.library_api.business.dto;

import com.diegoduarte.library_api.infrastructure.SexoEnum;
import com.diegoduarte.library_api.infrastructure.entity.ObrasEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutoresDTO {

    private Long id;
    private String nome;
    private SexoEnum sexo;
    private String email;
    private LocalDate dataNascimento;
    private String paisOrigem;
    private String cpf;
    private List<ObrasEntity> obras;

}
