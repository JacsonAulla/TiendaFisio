package com.back.fisiohaven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back.fisiohaven.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}