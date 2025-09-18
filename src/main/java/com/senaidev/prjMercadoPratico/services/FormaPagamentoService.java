package com.senaidev.prjMercadoPratico.services;

import com.senaidev.prjMercadoPratico.entities.FormaPagamento;
import com.senaidev.prjMercadoPratico.repositories.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> findAll() {
        return formaPagamentoRepository.findAll();
    }

    public FormaPagamento findById(Long id) {
        Optional<FormaPagamento> obj = formaPagamentoRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada"));
    }

    public FormaPagamento insert(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    public FormaPagamento update(Long id, FormaPagamento novoFormaPagamento) {
        FormaPagamento formaPagamento = findById(id);
        formaPagamento.setFormaPagamento(novoFormaPagamento.getFormaPagamento());
        return formaPagamentoRepository.save(formaPagamento);
    }

    public void delete(Long id) {
        formaPagamentoRepository.deleteById(id);
    }
}
