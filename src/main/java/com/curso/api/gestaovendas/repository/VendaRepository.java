package com.curso.api.gestaovendas.repository;

import com.curso.api.gestaovendas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Transactional
    @Query(value = "select * from venda where id_cliente = ?1 and ativo = 1", nativeQuery = true)
    Optional<List<Venda>> getByCliente_Id(Long id);

    @Transactional
    @Query(value = "select * from venda where ativo = 1", nativeQuery = true)
    Optional<List<Venda>> getAllVendas();

    @Transactional
    @Query(value = "select * from venda where data = ?1 and ativo = 1", nativeQuery = true)
    Optional<List<Venda>> getByData(LocalDate data);

    @Transactional
    @Modifying
    @Query(value = "update venda set ativo = 0 where id = ?1", nativeQuery = true)
    void deletar(Long id);
}
