package com.diegoduarte.library_api.infrastructure.entity;


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
@Table(name = "obras")
public class ObrasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "autorEmail")
    private String autorEmail;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", length = 240)
    private String descricao;

    @Column(name = "dataPublicacao")
    private LocalDate dataPublicacao;

    @Column(name = "dataExposicao")
    private LocalDate dataExposicao;

    @ManyToMany
    @JoinTable(
            name = "obra_autor",
            joinColumns = @JoinColumn(name = "obra_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<AutoresEntity> autores = new ArrayList<>();
}
