package com.diegoduarte.library_api.infrastructure.repository;

import com.diegoduarte.library_api.infrastructure.entity.ObrasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<ObrasEntity, Long> {
}
