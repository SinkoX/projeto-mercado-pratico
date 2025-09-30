package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

	Optional<FormaPagamento> findByFormaPagamento(String formaPagamento);

    List<FormaPagamento> findByFormaPagamentoIgnoreCase(String formaPagamento);

    boolean existsByFormaPagamentoIgnoreCase(String formaPagamento);
}
