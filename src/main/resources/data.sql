-- TIPOS DE USUÁRIO
INSERT INTO tb_tipo_usuario (nome_Tipo_Usuario, descricao) VALUES
('Cliente', 'Usuário comum que realiza compras'),
('Administrador', 'Responsável pela gestão do sistema'),
('Funcionário', 'Colaborador interno da empresa');

-- USUÁRIOS
INSERT INTO tb_usuario (email_usuario, senha_usuario, cpf_usuario, id_Tipo_Usuario) VALUES
('julio.botaccio@gmail.com', '12345', '23699040895', 1),
('maria@gmail.com', 'abcdef', '98765432100', 1);

-- FUNCIONÁRIOS
INSERT INTO tb_funcionario (nome_Funcionario, email_Funcionario, senha_Funcionario, cpf_Funcionario, cargo, id_Tipo_Usuario) VALUES
('Carlos Souza', 'carlos@mercado.com', 'func123', '11122233344', 'Atendente', 3);

-- FORNECEDORES
INSERT INTO tb_fornecedor (nome_Fornecedor, email_Fornecedor, cpf_Fornecedor, cnpj) VALUES
('Fornecedor A', 'fornecedorA@email.com', '22233344455', '12345678000199'),
('Fornecedor B', 'fornecedorB@email.com', '33344455566', '98765432000188');

-- PRODUTOS
INSERT INTO tb_produto (nome_Produto, preco_Produto, quantidade, categoria, data_Validade) VALUES
('Arroz 5kg', 25.90, 100, 'Alimento', '2026-01-01'),
('Feijão 1kg', 7.50, 200, 'Alimento', '2026-06-15'),
('Detergente', 2.99, 300, 'Limpeza', '2027-12-31');

-- ENTREGA
INSERT INTO tb_entrega (tempo_Entrega) VALUES
('2025-10-05'),
('2025-10-10');

-- FORMA DE PAGAMENTO
INSERT INTO tb_forma_pagamento (forma_Pagamento, status_Pagamento) VALUES
('Cartão de Crédito', 'Aprovado'),
('PIX', 'Pendente');

-- PEDIDO CLIENTE
INSERT INTO tb_pedido_usuario (status_Pedido_Cliente, data_Pedido, preco_Total, status, id_Usuario, id_Entrega, id_Funcionario, id_Forma_Pagamento) VALUES
('Finalizado', '2025-09-30', 33.40, 'Pago', 1, 1, 1, 1);

-- ASSOCIAÇÃO PEDIDO - PRODUTO
INSERT INTO pedido_usuario_produto (id_Pedido_Usuario, id_Produto) VALUES
(1, 1),
(1, 2);

-- PEDIDO FORNECEDOR
INSERT INTO tb_pedido_fornecedor (data_Pedido_Fornecedor, id_Produto, id_Fornecedor) VALUES
('2025-09-25', 3, 1);
