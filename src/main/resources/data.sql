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
ALTER TABLE tb_pedido_usuario AUTO_INCREMENT = 1;

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
INSERT INTO tb_usuario (email_usuario, senha_usuario, cpf_usuario, id_Tipo_Usuario) VALUES
('julio.botaccio@gmail.com', '12345', '23699040895', 1),
('maria@gmail.com', 'abcdef', '98765432100', 1);

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
('Arroz 5kg', 25.90, 100, '2026-01-01', 1),
('Feijão 1kg', 7.50, 200, '2026-06-15', 2),
('Macarrão Espaguete', 4.99, 150, '2026-02-10', 3),
('Detergente Líquido', 2.99, 300, '2027-12-31', 5),
('Coca-Cola 2L', 8.50, 250, '2026-03-20', 6);

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
-- 🔹 Agora que todos os IDs existem (1,1,1,1)
INSERT INTO tb_pedido_usuario (status_Pedido_Cliente, data_Pedido, preco_Total, status, id_Usuario, id_Entrega, id_Funcionario, id_Forma_Pagamento)
VALUES ('Finalizado', '2025-09-30', 33.40, 'Pago', 1, 1, 1, 1);

-- ======================================
-- ASSOCIAÇÃO PEDIDO - PRODUTO
-- ======================================
INSERT INTO tb_pedido_usuario_produto (id_Pedido_Usuario, id_Produto) VALUES
(1, 1),
(1, 2);

-- ======================================
-- PEDIDOS DE FORNECEDOR
-- ======================================
INSERT INTO tb_pedido_fornecedor (data_Pedido_Fornecedor, id_Produto, id_Fornecedor)
VALUES ('2025-09-25', 3, 1);
