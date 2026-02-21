package com.diegoduarte.library_api.infrastructure.entity;

import com.diegoduarte.library_api.infrastructure.SexoEnum;
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
@Entity
@Table(name = "autores")
public class AutoresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "dataNascimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Column(name = "paisOrigem", nullable = false)
    private String paisOrigem;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @ManyToMany(mappedBy = "autores")
    private List<ObrasEntity> obras = new ArrayList<>();
}
