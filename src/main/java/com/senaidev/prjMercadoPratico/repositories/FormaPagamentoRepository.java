package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

	Optional<FormaPagamento> findByFormaPagamento(String formaPagamento);

    List<FormaPagamento> findByFormaPagamentoIgnoreCase(String formaPagamento);

    boolean existsByFormaPagamentoIgnoreCase(String formaPagamento);
}
