package com.curso.api.gestaovendas.repository;

import com.curso.api.gestaovendas.model.Cliente;
import com.curso.api.gestaovendas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Transactional
    @Query(value = "select * from cliente where nome like %?1% and ativo = 1 order by nome limit 100", nativeQuery = true)
    public Optional<List<Cliente>> getByNome(String nome);

    @Transactional
    @Query(value = "select * from cliente where estado = ?1 and ativo = 1 order by nome limit 100", nativeQuery = true)
    public Optional<List<Cliente>> getByEstado(String estado);

    @Transactional
    @Query(value = "select * from cliente where cidade = ?1 and ativo = 1 order by nome limit 100", nativeQuery = true)
    public Optional<List<Cliente>> getByCidade(String cidade);

    public Optional<Cliente> findByNome(String nome);


}
