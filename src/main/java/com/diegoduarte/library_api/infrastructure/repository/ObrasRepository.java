package com.diegoduarte.library_api.infrastructure.repository;

import com.diegoduarte.library_api.infrastructure.entity.ObrasEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObrasRepository extends JpaRepository<ObrasEntity, Long> {

    Optional<ObrasEntity> findByNomeIgnoreCase(String titulo);

    List<ObrasEntity> findByAutorEmail(String email);






}
