package com.diegoduarte.library_api.infrastructure.repository;

import com.diegoduarte.library_api.infrastructure.entity.AutoresEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutoresRepository extends JpaRepository<AutoresEntity, Long> {

    boolean existsByEmail (String email); // verifica se o email existe

    Optional<AutoresEntity> findByEmail (String email);
    //optional: nao quebra o codigo, trata a excecao de uma forma melhor

    @Transactional //ajuda a causar nenhum erro na hora de deletar
    void deleteByEmail(String email);
}
