package com.curso.api.gestaovendas.repository;

import com.curso.api.gestaovendas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    Optional<List<Venda>> findByCliente_NomeOrderByData(String nome);
}
