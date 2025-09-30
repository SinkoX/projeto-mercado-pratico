-- ======================================
-- TIPOS DE USUÁRIO
-- ======================================
INSERT INTO tb_tipo_usuario (nome_Tipo_Usuario, descricao) VALUES
('Cliente', 'Usuário comum que realiza compras'),
('Administrador', 'Responsável pela gestão do sistema'),
('Funcionário', 'Colaborador interno da empresa')
ON DUPLICATE KEY UPDATE
nome_Tipo_Usuario = VALUES(nome_Tipo_Usuario),
descricao = VALUES(descricao);

-- ======================================
-- USUÁRIOS
-- ======================================
INSERT INTO tb_usuario (email_usuario, senha_usuario, cpf_usuario, id_Tipo_Usuario) VALUES
('julio.botaccio@gmail.com', '12345', '23699040895', 1),
('maria@gmail.com', 'abcdef', '98765432100', 1)
ON DUPLICATE KEY UPDATE
email_usuario = VALUES(email_usuario),
senha_usuario = VALUES(senha_usuario),
id_Tipo_Usuario = VALUES(id_Tipo_Usuario);

-- ======================================
-- FUNCIONÁRIOS
-- ======================================
INSERT INTO tb_funcionario (nome_Funcionario, email_Funcionario, senha_Funcionario, cpf_Funcionario, cargo, id_Tipo_Usuario) VALUES
('PzKing', 'pzKing@mercado.com', 'pzking123', '11122233344', 'Atendente', 3)
ON DUPLICATE KEY UPDATE
nome_Funcionario = VALUES(nome_Funcionario),
email_Funcionario = VALUES(email_Funcionario),
senha_Funcionario = VALUES(senha_Funcionario),
cargo = VALUES(cargo),
id_Tipo_Usuario = VALUES(id_Tipo_Usuario);

-- ======================================
-- FORNECEDORES
-- ======================================
INSERT INTO tb_fornecedor (nome_Fornecedor, email_Fornecedor, cpf_Fornecedor, cnpj) VALUES
('Fornecedor A', 'fornecedorA@email.com', '22233344455', '12345678000199'),
('Fornecedor B', 'fornecedorB@email.com', '33344455566', '98765432000188')
ON DUPLICATE KEY UPDATE
nome_Fornecedor = VALUES(nome_Fornecedor),
email_Fornecedor = VALUES(email_Fornecedor),
cnpj = VALUES(cnpj);

-- ======================================
-- CATEGORIAS
-- ======================================
INSERT INTO tb_categoria (nome_Categoria) VALUES
('Arroz e Feijão'),
('Massas e Molhos'),
('Bebidas'),
('Limpeza')
ON DUPLICATE KEY UPDATE
nome_Categoria = VALUES(nome_Categoria);

-- ======================================
-- SUBCATEGORIAS
-- ======================================
INSERT INTO tb_subcategoria (nome_Subcategoria, id_Categoria) VALUES
('Arroz', 1),
('Feijão', 1),
('Macarrão', 2),
('Molhos', 2),
('Detergentes', 4),
('Refrigerantes', 3)
ON DUPLICATE KEY UPDATE
nome_Subcategoria = VALUES(nome_Subcategoria),
id_Categoria = VALUES(id_Categoria);

-- Antes de inserir, limpe a tabela (opcional, para evitar duplicatas)
DELETE FROM tb_produto;

-- Inserção simples
INSERT INTO tb_produto (nome_Produto, preco_Produto, quantidade, data_Validade, id_Subcategoria, categoria) VALUES
('Arroz 5kg', 25.90, 100, '2026-01-01', 1, 'Arroz e Feijão'),
('Feijão 1kg', 7.50, 200, '2026-06-15', 2, 'Arroz e Feijão'),
('Macarrão Espaguete', 4.99, 150, '2026-02-10', 3, 'Massas e Molhos'),
('Detergente Líquido', 2.99, 300, '2027-12-31', 5, 'Limpeza'),
('Coca-Cola 2L', 8.50, 250, '2026-03-20', 6, 'Bebidas');


-- ======================================
-- ENTREGAS
-- ======================================
INSERT INTO tb_entrega (tempo_Entrega) VALUES
('2025-10-05'),
('2025-10-10')
ON DUPLICATE KEY UPDATE
tempo_Entrega = VALUES(tempo_Entrega);

-- ======================================
-- FORMAS DE PAGAMENTO
-- ======================================
INSERT INTO tb_forma_pagamento (forma_Pagamento, status_Pagamento) VALUES
('Cartão de Crédito', 'Aprovado'),
('PIX', 'Pendente')
ON DUPLICATE KEY UPDATE
status_Pagamento = VALUES(status_Pagamento);

-- ======================================
-- PEDIDOS DE CLIENTE
-- ======================================
INSERT INTO tb_pedido_usuario (status_Pedido_Cliente, data_Pedido, preco_Total, status, id_Usuario, id_Entrega, id_Funcionario, id_Forma_Pagamento) VALUES
('Finalizado', '2025-09-30', 33.40, 'Pago', 1, 1, 1, 1)
ON DUPLICATE KEY UPDATE
status_Pedido_Cliente = VALUES(status_Pedido_Cliente),
data_Pedido = VALUES(data_Pedido),
preco_Total = VALUES(preco_Total),
status = VALUES(status),
id_Usuario = VALUES(id_Usuario),
id_Entrega = VALUES(id_Entrega),
id_Funcionario = VALUES(id_Funcionario),
id_Forma_Pagamento = VALUES(id_Forma_Pagamento);

-- ======================================
-- ASSOCIAÇÃO PEDIDO - PRODUTO
-- ======================================
INSERT INTO pedido_usuario_produto (id_Pedido_Usuario, id_Produto) VALUES
(1, 1),
(1, 2)
ON DUPLICATE KEY UPDATE
id_Produto = VALUES(id_Produto);

-- ======================================
-- PEDIDOS DE FORNECEDOR
-- ======================================
INSERT INTO tb_pedido_fornecedor (data_Pedido_Fornecedor, id_Produto, id_Fornecedor) VALUES
('2025-09-25', 3, 1)
ON DUPLICATE KEY UPDATE
data_Pedido_Fornecedor = VALUES(data_Pedido_Fornecedor),
id_Produto = VALUES(id_Produto),
id_Fornecedor = VALUES(id_Fornecedor);
