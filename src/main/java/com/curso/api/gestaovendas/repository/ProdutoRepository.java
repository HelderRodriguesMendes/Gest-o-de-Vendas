package com.curso.api.gestaovendas.repository;

import com.curso.api.gestaovendas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Transactional
    @Query(value = "select * from produto where descricao like %?1% and ativo = 1 order by descricao limit 100", nativeQuery = true)
    public Optional<List<Produto>>getByNome(String nome);

    @Transactional
    @Query(value = "select * from produto where id_categoria = ?1 and descricao = ?2 and ativo = 1", nativeQuery = true)
    public Optional<Produto> getByCategoriaIdAndDescricao( Long idCategoria, String descricao);
}
