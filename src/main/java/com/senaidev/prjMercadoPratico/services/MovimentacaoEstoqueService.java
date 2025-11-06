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

    // 🔹 Listar todas as movimentações
    public List<MovimentacaoEstoqueDTO> listarTodas() {
        return movimentacaoRepository.findAll().stream()
                .map(MovimentacaoEstoqueDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Buscar movimentação por ID
    public MovimentacaoEstoqueDTO buscarPorId(Long id) {
        MovimentacaoEstoque movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimentação não encontrada com ID: " + id));
        return new MovimentacaoEstoqueDTO(movimentacao);
    }

    // 🔹 Buscar movimentações por produto
    public List<MovimentacaoEstoqueDTO> buscarPorProduto(Long idProduto) {
        return movimentacaoRepository.findByProdutoIdProduto(idProduto).stream()
                .map(MovimentacaoEstoqueDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Buscar movimentações por tipo
    public List<MovimentacaoEstoqueDTO> buscarPorTipo(TipoMovimentacao tipo) {
        return movimentacaoRepository.findByTipoMovimentacao(tipo).stream()
                .map(MovimentacaoEstoqueDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 Buscar movimentações por período
    public List<MovimentacaoEstoqueDTO> buscarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return movimentacaoRepository.findByPeriodo(dataInicio, dataFim).stream()
                .map(MovimentacaoEstoqueDTO::new)
                .collect(Collectors.toList());
    }

    // 🔹 **REGISTRAR SAÍDA** - Quando PedidoUsuario é PAGO
    @Transactional
    public void registrarSaida(PedidoUsuario pedidoUsuario) {
        pedidoUsuario.getItensPedido().forEach(item -> {
            Produto produto = item.getProduto();
            Integer quantidade = item.getQuantidade();

            Estoque estoque = estoqueRepository.findByProduto(produto)
                    .orElseThrow(() -> new IllegalStateException("Estoque não encontrado para o produto: " + produto.getNomeProduto()));

            // Remove quantidade do estoque
            estoque.removerQuantidade(quantidade);
            estoqueRepository.save(estoque);

            // Registra a movimentação
            MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(
                    produto, estoque, quantidade, pedidoUsuario,
                    "Saída automática - Pedido do usuário #" + pedidoUsuario.getIdPedidoUsuario()
            );
            movimentacaoRepository.save(movimentacao);
        });
    }

    // 🔹 **REGISTRAR ENTRADA** - Quando PedidoFornecedor é RECEBIDO
    @Transactional
    public void registrarEntrada(PedidoFornecedor pedidoFornecedor) {
        pedidoFornecedor.getItensPedidoFornecedor().forEach(item -> {
            Produto produto = item.getProduto();
            Integer quantidade = item.getQuantidade();

            Estoque estoque = estoqueRepository.findByProduto(produto)
                    .orElseThrow(() -> new IllegalStateException("Estoque não encontrado para o produto: " + produto.getNomeProduto()));

            // Adiciona quantidade no estoque
            estoque.adicionarQuantidade(quantidade);
            estoqueRepository.save(estoque);

            // Registra a movimentação
            MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(
                    produto, estoque, quantidade, pedidoFornecedor,
                    "Entrada automática - Pedido do fornecedor #" + pedidoFornecedor.getIdPedidoFornecedor()
            );
            movimentacaoRepository.save(movimentacao);
        });
    }

    // 🔹 Registrar movimentação manual (ENTRADA ou SAÍDA)
    @Transactional
    public MovimentacaoEstoqueDTO registrarMovimentacaoManual(Long idProduto, Integer quantidade, 
                                                              TipoMovimentacao tipo, String observacao) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + idProduto));

        Estoque estoque = estoqueRepository.findByProduto(produto)
                .orElseThrow(() -> new IllegalStateException("Estoque não encontrado para o produto: " + produto.getNomeProduto()));

        // Atualiza o estoque
        if (tipo == TipoMovimentacao.ENTRADA) {
            estoque.adicionarQuantidade(quantidade);
        } else {
            estoque.removerQuantidade(quantidade);
        }
        estoqueRepository.save(estoque);

        // Registra movimentação
        MovimentacaoEstoque movimentacao = tipo == TipoMovimentacao.ENTRADA ?
                new MovimentacaoEstoque(produto, estoque, quantidade, (PedidoFornecedor) null, observacao) :
                new MovimentacaoEstoque(produto, estoque, quantidade, (PedidoUsuario) null, observacao);

        movimentacao = movimentacaoRepository.save(movimentacao);
        return new MovimentacaoEstoqueDTO(movimentacao);
    }

    // 🔹 Buscar últimas movimentações
    public List<MovimentacaoEstoqueDTO> buscarUltimasMovimentacoes() {
        return movimentacaoRepository.findUltimasMovimentacoes().stream()
                .limit(50)
                .map(MovimentacaoEstoqueDTO::new)
                .collect(Collectors.toList());
    }
}