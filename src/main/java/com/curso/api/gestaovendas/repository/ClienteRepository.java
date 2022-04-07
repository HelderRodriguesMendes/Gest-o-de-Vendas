package com.curso.api.gestaovendas.repository;

import com.curso.api.gestaovendas.model.Cliente;
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
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Transactional
    @Query(value = "select * from cliente where ativo = 1 order by id", nativeQuery = true)
    public Page<Cliente> findAll(Pageable pageable);

    @Transactional
    @Query(value = "select * from cliente where nome like %?1% order by nome limit 100", nativeQuery = true)
    Optional<List<Cliente>> getByNomeLike(String nome);

    @Transactional
    @Query(value = "select * from cliente where estado = ?1 and ativo = 1 order by nome limit 100", nativeQuery = true)
    public Optional<List<Cliente>> getByEstado(String estado);

    @Transactional
    @Query(value = "select * from cliente where cidade = ?1 and ativo = 1 order by nome limit 100", nativeQuery = true)
    public Optional<List<Cliente>> getByCidade(String cidade);

    @Transactional
    @Query(value = "select * from cliente where nome = ?1 and ativo = 1 order by nome limit 100", nativeQuery = true)
    public Optional<Cliente> getByNome(String nome);

    @Transactional
    @Modifying
    @Query(value = "update cliente set ativo = 0 where id = ?1", nativeQuery = true)
    public void deletar(Long id);
}
