package com.diegoduarte.library_api.infrastructure.repository;

import com.diegoduarte.library_api.infrastructure.entity.AutoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<AutoresEntity, Long> {
}
