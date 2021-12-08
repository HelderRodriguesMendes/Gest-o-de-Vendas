package com.curso.api.gestaovendas.repository;

import com.curso.api.gestaovendas.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByNome(String nome);

    @Transactional
    @Query(value = "select * from categoria where nome like %?1% order by nome limit 100", nativeQuery = true)
    Optional<List<Categoria>> getByNome(String nome);
}
