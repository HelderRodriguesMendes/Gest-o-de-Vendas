package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.exception.entidadesEnum.EntidadesMsgException;
import com.curso.api.gestaovendas.model.ItemVenda;
import com.curso.api.gestaovendas.model.Produto;
import com.curso.api.gestaovendas.model.Venda;
import com.curso.api.gestaovendas.repository.ItemVendaRepository;
import com.curso.api.gestaovendas.requestDTO.ItemVendaRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ProdutoService produtoService;

    public List<ItemVenda> salvar(Venda vendaSalva, List<ItemVendaRequestDTO> itemVendaRequestDTOS){
        List<ItemVenda> itemVendasSalvas = new ArrayList<>();
        itemVendaRequestDTOS.forEach(itemVendaRequestDTO -> {

            Produto produto = this.validateProdutoExist(itemVendaRequestDTO.getIdProduto());
            produto.setQuantidade(produto.getQuantidade() - itemVendaRequestDTO.getQuantidade());

            ItemVenda itemVenda = new ItemVenda(itemVendaRequestDTO.getQuantidade(), itemVendaRequestDTO.getPrecoVendido(), produto, vendaSalva, true);
            itemVendasSalvas.add(itemVendaRepository.save(itemVenda));
        });
        return itemVendasSalvas;
    }

    public List<ItemVenda> getItemVendaByIdVenda(Long idVenda){
        Optional<List<ItemVenda>> optionalItemVendas = itemVendaRepository.getByVenda_Id(idVenda);
        if(optionalItemVendas.get().size() <= 0){
            throw new EmptyResultDataAccessException(EntidadesMsgException.ITENVENDA.getEntidade(), 1);
        }
        return optionalItemVendas.get();
    }


    public List<ItemVenda> atualizar(List<ItemVendaRequestDTO> itemVendaRequestDTOS, Long idVenda){
        List<ItemVenda> itemVendas = new ArrayList<>();
        List<ItemVenda> itemVendasSalvo = getItemVendaByIdVenda(idVenda);

        itemVendaRequestDTOS.forEach(itemVendaRequestDTO -> {
            itemVendasSalvo.forEach(itemVendaSalvo -> {
                Produto produto = produtoService.getById(itemVendaRequestDTO.getIdProduto());
                int diferencaQuantidade = 0;

                if(itemVendaRequestDTO.getQuantidade() > itemVendaSalvo.getQuantidade()){
                    diferencaQuantidade = itemVendaRequestDTO.getQuantidade() - itemVendaSalvo.getQuantidade();
                }else if(itemVendaRequestDTO.getQuantidade() < itemVendaSalvo.getQuantidade()){
                    int reducao = itemVendaSalvo.getQuantidade() - itemVendaRequestDTO.getQuantidade();
                    produto.setQuantidade(produto.getQuantidade() + reducao);
                }

                if(diferencaQuantidade > 0){
                    verificarQtdItemVenda(produto, diferencaQuantidade);
                    produto.setQuantidade(produto.getQuantidade() - diferencaQuantidade);
                }

                if(itemVendaRequestDTO.getIdProduto() == itemVendaSalvo.getProduto().getId()){
                    produtoService.atualizar(produto);
                    itemVendaSalvo.setQuantidade(itemVendaRequestDTO.getQuantidade());
                    itemVendaSalvo.setPrecoVendido(itemVendaRequestDTO.getPrecoVendido());
                    itemVendas.add(itemVendaRepository.save(itemVendaSalvo));
                }
            });
        });
        return itemVendas;
    }

    public void deletar(Long id){
        itemVendaRepository.deleteById(id);
    }

    public void validarItemVenda(List<ItemVendaRequestDTO> itemVendaRequestDTOS){
        itemVendaRequestDTOS.forEach(itemVendaRequestDTO -> {
            Produto produto = this.validateProdutoExist(itemVendaRequestDTO.getIdProduto());
            verificarQtdItemVenda(produto, itemVendaRequestDTO.getQuantidade());
        });
    }

    private void verificarQtdItemVenda(Produto produto, int qtd){
        if(produto.getQuantidade() < qtd){
            this.msgValidacaoQtdItemVenda(produto);
        }
    }

    private void msgValidacaoQtdItemVenda(Produto produto){
        throw new RegraNegocioException("Há apenas " + produto.getQuantidade() + " unidades disponiveis do produto " + produto.getDescricao());
    }

    private Produto validateProdutoExist(Long idProduto){
        if(idProduto == null){
            throw new RegraNegocioException("Informe o produto");
        }
        return produtoService.getById(idProduto);
    }
}
