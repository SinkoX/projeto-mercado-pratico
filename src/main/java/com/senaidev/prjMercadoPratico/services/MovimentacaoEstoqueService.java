package com.senaidev.prjMercadoPratico.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senaidev.prjMercadoPratico.dto.MovimentacaoEstoqueDTO;
import com.senaidev.prjMercadoPratico.entities.Estoque;
import com.senaidev.prjMercadoPratico.entities.MovimentacaoEstoque;
import com.senaidev.prjMercadoPratico.entities.PedidoFornecedor;
import com.senaidev.prjMercadoPratico.entities.PedidoUsuario;
import com.senaidev.prjMercadoPratico.entities.Produto;
import com.senaidev.prjMercadoPratico.enums.TipoMovimentacao;
import com.senaidev.prjMercadoPratico.repositories.EstoqueRepository;
import com.senaidev.prjMercadoPratico.repositories.MovimentacaoEstoqueRepository;
import com.senaidev.prjMercadoPratico.repositories.ProdutoRepository;

@Service
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoRepository;
    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository movimentacaoRepository,
                                      EstoqueRepository estoqueRepository,
                                      ProdutoRepository produtoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<MovimentacaoEstoqueDTO> listarTodas() {
        return movimentacaoRepository.findAll().stream()
                .map(MovimentacaoEstoqueDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public MovimentacaoEstoqueDTO buscarPorId(Long id) {
        MovimentacaoEstoque m = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimentação não encontrada: " + id));
        return MovimentacaoEstoqueDTO.fromEntity(m);
    }

    public List<MovimentacaoEstoqueDTO> buscarPorProduto(Long idProduto) {
        return movimentacaoRepository.findByProdutoIdProduto(idProduto).stream()
                .map(MovimentacaoEstoqueDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<MovimentacaoEstoqueDTO> buscarPorTipo(TipoMovimentacao tipo) {
        return movimentacaoRepository.findByTipoMovimentacao(tipo).stream()
                .map(MovimentacaoEstoqueDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<MovimentacaoEstoqueDTO> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return movimentacaoRepository.findByPeriodo(inicio, fim).stream()
                .map(MovimentacaoEstoqueDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void registrarSaida(PedidoUsuario pedidoUsuario) {
        pedidoUsuario.getItensPedido().forEach(item -> {
            Produto produto = item.getProduto();
            Integer quantidade = item.getQuantidade();

            Estoque estoque = estoqueRepository.findByProduto(produto)
                    .orElseThrow(() -> new IllegalStateException("Estoque não encontrado: " + produto.getNomeProduto()));
            estoque.removerQuantidade(quantidade);
            estoqueRepository.save(estoque);

            MovimentacaoEstoque m = MovimentacaoEstoque.criarSaida(
                    produto, estoque, quantidade, pedidoUsuario,
                    "Saída automática - Pedido #" + pedidoUsuario.getIdPedidoUsuario()
            );
            movimentacaoRepository.save(m);
        });
    }

    @Transactional
    public void registrarEntrada(PedidoFornecedor pedidoFornecedor) {
        pedidoFornecedor.getItensPedidoFornecedor().forEach(item -> {
            Produto produto = item.getProduto();
            Integer quantidade = item.getQuantidade();

            Estoque estoque = estoqueRepository.findByProduto(produto)
                    .orElseThrow(() -> new IllegalStateException("Estoque não encontrado: " + produto.getNomeProduto()));
            estoque.adicionarQuantidade(quantidade);
            estoqueRepository.save(estoque);

            MovimentacaoEstoque m = MovimentacaoEstoque.criarEntrada(
                    produto, estoque, quantidade, pedidoFornecedor,
                    "Entrada automática - Pedido #" + pedidoFornecedor.getIdPedidoFornecedor()
            );
            movimentacaoRepository.save(m);
        });
    }

    @Transactional
    public MovimentacaoEstoqueDTO registrarMovimentacaoManual(Long idProduto, Integer quantidade,
                                                              TipoMovimentacao tipo, String observacao) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + idProduto));
        Estoque estoque = estoqueRepository.findByProduto(produto)
                .orElseThrow(() -> new IllegalStateException("Estoque não encontrado: " + produto.getNomeProduto()));

        if (tipo == TipoMovimentacao.ENTRADA) {
            estoque.adicionarQuantidade(quantidade);
        } else {
            estoque.removerQuantidade(quantidade);
        }
        estoqueRepository.save(estoque);

        MovimentacaoEstoque m = MovimentacaoEstoque.criarManual(produto, estoque, quantidade, tipo, observacao);
        m = movimentacaoRepository.save(m);

        return MovimentacaoEstoqueDTO.fromEntity(m);
    }

    public List<MovimentacaoEstoqueDTO> buscarUltimasMovimentacoes() {
        return movimentacaoRepository.findUltimasMovimentacoes().stream()
                .limit(50)
                .map(MovimentacaoEstoqueDTO::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public MovimentacaoEstoqueDTO registrarMovimentacaoManual(MovimentacaoEstoqueDTO dto) {
        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + dto.getIdProduto()));

        Estoque estoque = estoqueRepository.findByProduto(produto)
                .orElseThrow(() -> new IllegalStateException("Estoque não encontrado: " + produto.getNomeProduto()));

        if (dto.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
            estoque.adicionarQuantidade(dto.getQuantidade());
        } else {
            estoque.removerQuantidade(dto.getQuantidade());
        }
        estoqueRepository.save(estoque);

        MovimentacaoEstoque m = MovimentacaoEstoque.criarManual(
                produto,
                estoque,
                dto.getQuantidade(),
                dto.getTipoMovimentacao(),
                dto.getObservacao()
        );

        return MovimentacaoEstoqueDTO.fromEntity(movimentacaoRepository.save(m));
    }

    
}
