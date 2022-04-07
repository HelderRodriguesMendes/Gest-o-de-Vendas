package com.curso.api.gestaovendas.repository;

import com.curso.api.gestaovendas.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    @Transactional
    @Query(value = "select * from item_venda where id_venda = ?1 and ativo = 1 order by preco_vendido", nativeQuery = true)
    Optional<List<ItemVenda>> getByVenda_Id(Long idVenda);

    @Transactional
    @Modifying
    @Query(value = "update item_venda set ativo = 0 where id = ?1", nativeQuery = true)
    public void deletar(Long id);
}
