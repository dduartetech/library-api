package com.diegoduarte.library_api.business.dto;

import com.diegoduarte.library_api.infrastructure.entity.AutoresEntity;
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
public class ObrasDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataPublicacao;
    private LocalDate dataExposicao;
    private List<String> autoresEmails;
}

