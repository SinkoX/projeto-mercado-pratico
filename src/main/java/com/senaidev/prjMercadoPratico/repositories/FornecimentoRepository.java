package com.senaidev.prjMercadoPratico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senaidev.prjMercadoPratico.entities.Fornecedor;
import com.senaidev.prjMercadoPratico.entities.Fornecimento;

@Repository
public interface FornecimentoRepository extends JpaRepository<Fornecimento, Long> {
	 List<Fornecimento> findByFornecedor(Fornecedor fornecedor);
}
