package com.senaidev.prjMercadoPratico.services;

import com.senaidev.prjMercadoPratico.entities.Fornecimento;
import com.senaidev.prjMercadoPratico.entities.ItemFornecimento;
import com.senaidev.prjMercadoPratico.repositories.FornecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FornecimentoService {

    @Autowired
    private FornecimentoRepository fornecimentoRepository;

    @Autowired
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    @Transactional
    public Fornecimento insert(Fornecimento fornecimento) {
        // Salva o fornecimento
        Fornecimento novo = fornecimentoRepository.save(fornecimento);

        // Para cada item do fornecimento, registra entrada no estoque
        for (ItemFornecimento item : novo.getItens()) {
            movimentacaoEstoqueService.registrarMovimentacaoManual(
                item.getProduto().getIdProduto(),
                item.getQuantidade(),
                com.senaidev.prjMercadoPratico.enums.TipoMovimentacao.ENTRADA,
                "Entrada automática - Fornecimento #" + novo.getIdFornecimento()
            );
        }

        return novo;
    }

    public java.util.List<Fornecimento> findAll() {
        return fornecimentoRepository.findAll();
    }

    public Fornecimento findById(Long id) {
        return fornecimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecimento não encontrado"));
    }

    public void delete(Long id) {
        fornecimentoRepository.deleteById(id);
    }
}
