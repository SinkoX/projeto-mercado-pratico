package com.senaidev.prjMercadoPratico.services;

import com.senaidev.prjMercadoPratico.entities.Entrega;
import com.senaidev.prjMercadoPratico.repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    // 🔍 Buscar todas as entregas
    public List<Entrega> findAll() {
        return entregaRepository.findAll();
    }

    // 🔍 Buscar por ID
    public Entrega findById(Long id) {
        Optional<Entrega> obj = entregaRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Entrega não encontrada com ID: " + id));
    }

    // ✅ Inserir nova entrega
    public Entrega insert(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    // ♻️ Atualizar entrega existente
    public Entrega update(Long id, Entrega novaEntrega) {
        Entrega entrega = findById(id);
        entrega.setTempoEntrega(novaEntrega.getTempoEntrega());
        return entregaRepository.save(entrega);
    }

    // ❌ Deletar por ID
    public void delete(Long id) {
        entregaRepository.deleteById(id);
    }
}
