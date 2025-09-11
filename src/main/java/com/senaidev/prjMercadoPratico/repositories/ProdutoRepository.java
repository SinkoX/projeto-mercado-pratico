package com.senaidev.prjMercadoPratico.repositories;

import com.senaidev.prjMercadoPratico.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
