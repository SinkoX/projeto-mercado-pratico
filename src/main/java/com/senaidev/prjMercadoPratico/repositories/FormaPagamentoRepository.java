package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    //  Buscar uma forma de pagamento pelo tipo (exato)
    Optional<FormaPagamento> findByTipo(String tipo);

    // Buscar por tipo ignorando maiúsculas/minúsculas
    List<FormaPagamento> findByTipoIgnoreCase(String tipo);

    // Verificar se já existe um tipo de pagamento
    boolean existsByTipoIgnoreCase(String tipo);
}
