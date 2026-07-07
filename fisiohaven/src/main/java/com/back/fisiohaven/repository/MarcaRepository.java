package com.back.fisiohaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back.fisiohaven.models.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}