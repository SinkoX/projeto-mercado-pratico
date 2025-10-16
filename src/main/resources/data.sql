-- ======================================
-- LIMPEZA DAS TABELAS
-- ======================================
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM tb_pedido_fornecedor;
DELETE FROM tb_pedido_usuario;
DELETE FROM tb_produto;
DELETE FROM tb_subcategoria;
DELETE FROM tb_categoria;
DELETE FROM tb_fornecedor;
DELETE FROM tb_funcionario;
DELETE FROM tb_usuario;
DELETE FROM tb_tipo_usuario;
DELETE FROM tb_entrega;
DELETE FROM tb_forma_pagamento;

ALTER TABLE tb_tipo_usuario AUTO_INCREMENT = 1;
ALTER TABLE tb_usuario AUTO_INCREMENT = 1;
ALTER TABLE tb_funcionario AUTO_INCREMENT = 1;
ALTER TABLE tb_fornecedor AUTO_INCREMENT = 1;
ALTER TABLE tb_categoria AUTO_INCREMENT = 1;
ALTER TABLE tb_subcategoria AUTO_INCREMENT = 1;
ALTER TABLE tb_produto AUTO_INCREMENT = 1;
ALTER TABLE tb_entrega AUTO_INCREMENT = 1;
ALTER TABLE tb_forma_pagamento AUTO_INCREMENT = 1;

SET FOREIGN_KEY_CHECKS = 1;

-- ======================================
-- TIPOS DE USUÁRIO
-- ======================================
INSERT INTO tb_tipo_usuario (nome_Tipo_Usuario, descricao) VALUES
('Cliente', 'Usuário comum que realiza compras'),
('Administrador', 'Responsável pela gestão do sistema'),
('Funcionário', 'Colaborador interno da empresa');

-- ======================================
-- USUÁRIOS
-- ======================================
INSERT INTO tb_usuario (email_usuario, nome_usuario, senha_usuario, cpf_usuario, id_Tipo_Usuario) VALUES
('julio.botaccio@gmail.com','Júlio César', '12345', '23699040895', 2),
('maria@gmail.com','Maria', 'abcdef', '98765432100', 1);

-- ======================================
-- FUNCIONÁRIOS
-- ======================================
INSERT INTO tb_funcionario (nome_Funcionario, email_Funcionario, senha_Funcionario, cpf_Funcionario, cargo, id_Tipo_Usuario) VALUES
('PzKing', 'pzKing@mercado.com', 'pzking123', '11122233344', 'Atendente', 3);

-- ======================================
-- FORNECEDORES
-- ======================================
INSERT INTO tb_fornecedor (nome_Fornecedor, email_Fornecedor, cpf_Fornecedor, cnpj) VALUES
('Fornecedor A', 'fornecedorA@email.com', '22233344455', '12345678000199'),
('Fornecedor B', 'fornecedorB@email.com', '33344455566', '98765432000188');

-- ======================================
-- CATEGORIAS
-- ======================================
INSERT INTO tb_categoria (nome_Categoria) VALUES
('Arroz e Feijão'),
('Massas e Molhos'),
('Bebidas'),
('Limpeza');

-- ======================================
-- SUBCATEGORIAS
-- ======================================
INSERT INTO tb_subcategoria (nome_Subcategoria, id_Categoria) VALUES
('Arroz', 1),
('Feijão', 1),
('Macarrão', 2),
('Molhos', 2),
('Detergentes', 4),
('Refrigerantes', 3);

-- ======================================
-- PRODUTOS
-- ======================================
INSERT INTO tb_produto (nome_Produto, preco_Produto, quantidade, data_Validade, id_Subcategoria) VALUES
('Arroz 10kg', 49.90, 120, '2026-12-01', 1),
('Feijão Carioca 5kg', 25.00, 150, '2026-07-20', 2),
('Macarrão Penne', 6.99, 180, '2026-04-15', 3),
('Macarrão para Lasanha', 7.50, 160, '2026-05-10', 3),
('Óleo de Soja 900ml', 4.50, 200, '2027-03-15', 2),
('Detergente Ypê 500ml', 1.99, 220, '2027-06-30', 5),
('Coca-Cola Lata 350ml', 3.20, 300, '2026-08-20', 6),
('Fanta Uva 2L', 6.50, 250, '2026-07-10', 6),
('Sabão em Pó Omo 1kg', 12.90, 180, '2027-01-05', 4),
('Desinfetante Pinho Sol 500ml', 3.80, 230, '2027-09-01', 5),
('Refrigerante Guaraná Antártica 2L', 7.90, 180, '2026-10-10', 6),
('Água Mineral 1,5L', 2.99, 300, '2027-11-30', 3);


-- ======================================
-- ENTREGAS
-- ======================================
INSERT INTO tb_entrega (tempo_Entrega) VALUES
('2025-10-05'),
('2025-10-10');

-- ======================================
-- FORMAS DE PAGAMENTO
-- ======================================
INSERT INTO tb_forma_pagamento (forma_Pagamento, status_Pagamento) VALUES
('Cartão de Crédito', 'Aprovado'),
('PIX', 'Pendente');

-- ======================================
-- PEDIDOS DE CLIENTE
-- ======================================
INSERT INTO tb_pedido_usuario (
  id_carrinho,
  valor_total,
  status_pedido_usuario,
  data_pedido_usuario,
  id_Usuario,
  id_Entrega,
  id_Funcionario,
  id_Forma_Pagamento
)
VALUES (NULL, 33.40, 'Finalizado', '2025-09-30', 1, 1, 1, 1);


-- ======================================
-- PEDIDOS DE FORNECEDOR
-- ======================================
INSERT INTO tb_pedido_fornecedor (data_Pedido_Fornecedor, id_Produto, id_Fornecedor)
VALUES ('2025-09-25', 3, 1);
