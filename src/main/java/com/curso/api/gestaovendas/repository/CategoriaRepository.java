package com.curso.api.gestaovendas.repository;

import com.curso.api.gestaovendas.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Transactional
    @Query(value = "select * from categoria where ativo = 1 order by id", nativeQuery = true)
    public Page<Categoria> findAll(Pageable pageable);

    @Transactional
    @Query(value = "select * from categoria where ativo = 1 and id = ?1 order by id", nativeQuery = true)
    public Optional<Categoria> findById(Long id);

    @Transactional
    @Query(value = "select * from categoria where nome = ?1 and ativo = 1", nativeQuery = true)
    Categoria getByNome(String nome);

    @Transactional
    @Query(value = "select * from categoria where nome like %?1% order by nome limit 100", nativeQuery = true)
    Optional<List<Categoria>> getByNomeLike(String nome);

    @Transactional
    @Modifying
    @Query(value = "update categoria set ativo = 0 where id = ?1", nativeQuery = true)
    public void deletar(Long id);
}
