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

ALTER TABLE tb_tipo_usuario AUTO_INCREMENT = 1;
ALTER TABLE tb_usuario AUTO_INCREMENT = 1;
ALTER TABLE tb_funcionario AUTO_INCREMENT = 1;
ALTER TABLE tb_fornecedor AUTO_INCREMENT = 1;
ALTER TABLE tb_categoria AUTO_INCREMENT = 1;
ALTER TABLE tb_subcategoria AUTO_INCREMENT = 1;
ALTER TABLE tb_produto AUTO_INCREMENT = 1;

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
INSERT INTO tb_usuario (email_usuario, nome_usuario, senha_usuario, cpf_usuario, telefone_usuario, id_Tipo_Usuario) VALUES
('julio.botaccio@gmail.com','Júlio César', '12345', '23699040895', '15981923040', 2),
('maria@gmail.com','Maria', 'abcdef', '98765432100', '15991402029', 1);

-- ======================================
-- ENDEREÇOS
-- ======================================
INSERT INTO tb_endereco (cep, rua, numero, bairro, cidade, complemento, id_usuario) VALUES
('18010-000', 'Rua XV de Novembro', '123', 'Centro', 'Sorocaba', 'Apartamento 45', 1),
('18020-050', 'Avenida São Paulo', '987', 'Jardim Paulista', 'Sorocaba', 'Casa 2', 2);

-- ======================================
-- FUNCIONÁRIOS
-- ======================================
INSERT INTO tb_funcionario (nome_Funcionario, email_Funcionario, senha_Funcionario, cpf_Funcionario, telefone_funcionario, cargo, id_Tipo_Usuario) VALUES
('PzKing', 'pzKing@mercado.com', 'pzking123', '11122233344', '15972814010', 'Atendente', 3);

-- ======================================
-- FORNECEDORES
-- ======================================
INSERT INTO tb_fornecedor (nome_Fornecedor, email_Fornecedor, cpf_Fornecedor, telefone_fornecedor, cnpj) VALUES
('Fornecedor A', 'fornecedorA@email.com', '22233344455', '15999282837', '12345678000199'),
('Fornecedor B', 'fornecedorB@email.com', '33344455566', '15981725747', '98765432000188');

-- ======================================
-- CATEGORIAS
-- ======================================
INSERT INTO tb_categoria (nome_Categoria) VALUES
('hortifruti'),
('bebidas'),
('mercearia'),
('limpeza'),
('açougue'),
('higiene'),
('padaria'),
('petshop');

-- ======================================
-- SUBCATEGORIAS
-- ======================================
INSERT INTO tb_subcategoria (nome_Subcategoria, id_Categoria) VALUES
('Frutas', 1),
('Verduras', 1),
('Refrigerantes', 2),
('Sucos', 2),
('Arroz', 3),
('Feijão', 3),
('Macarrão', 3),
('Detergentes', 4),
('Sabão em pó', 4),
('Carnes', 5),
('Papel Higiênico', 6),
('Pães', 7),
('Rações', 8);

-- ======================================
-- PRODUTOS
-- ======================================
-- Hortifruti
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, img_url, descricao_produto) VALUES
('Banana Nanica', 6.50, 200, '2025-11-30', 1, 'https://example.com/banana.png', 'Banana fresca e saborosa.'),
('Alface Crespa', 3.20, 150, '2025-11-28', 2, 'https://example.com/alface.png', 'Alface fresca e crocante.');

-- Bebidas
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, img_url, descricao_produto) VALUES
('Coca-Cola Lata 350ml', 3.20, 300, '2026-08-20', 2, 'https://superprix.vteximg.com.br/arquivos/ids/210608-600-600/BMo6Zlso.png', 'Coca-Cola Original em lata.'),
('Fanta Uva 2L', 6.50, 250, '2026-07-10', 2, 'https://superprix.vteximg.com.br/arquivos/ids/226361-600-600/Refrigerante-Fanta-Uva-2l.png', 'Refrigerante Fanta sabor uva.'),
('Suco de Laranja 1L', 5.50, 200, '2026-09-01', 2, 'https://example.com/suco-laranja.png', 'Suco de laranja natural.');

-- Mercearia
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, img_url, descricao_produto) VALUES
('Arroz 10kg', 49.90, 120, '2026-12-01', 3, 'https://superprix.vteximg.com.br/arquivos/ids/174487/Arroz-Tio-Joao-Branco-1kg.png', 'Arroz tipo 1 de qualidade premium, grãos longos e soltinhos.'),
('Feijão Carioca 5kg', 25.00, 150, '2026-07-20', 3, 'https://kicaldo.com.br/wp-content/uploads/2020/07/Kicaldo-feijaocarioca.png', 'Feijão carioca selecionado, grãos firmes e sabor marcante.'),
('Macarrão Penne', 6.99, 180, '2026-04-15', 3, 'https://vitarella.com.br/wp-content/uploads/2020/11/speciale_Semola_penne_400g-792x792-1.png', 'Macarrão tipo penne de alta qualidade.');

-- Limpeza
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, img_url, descricao_produto) VALUES
('Detergente Ypê 500ml', 1.99, 220, '2027-06-30', 4, 'https://castronaves.vteximg.com.br/arquivos/ids/371251-1000-1000/84574_01.jpg', 'Detergente Ypê com fórmula concentrada.'),
('Sabão em Pó Omo 1kg', 12.90, 180, '2027-01-05', 4, 'https://cdn-cosmos.bluesoft.com.br/products/7891150018587', 'Sabão em pó Omo Multiação.'),
('Desinfetante Pinho Sol 500ml', 3.80, 230, '2027-09-01', 4, 'https://superprix.vteximg.com.br/arquivos/ids/176738/Desinfetante-Pinho-Sol-Lavanda-500ml.png', 'Desinfetante Pinho Sol com fragrância marcante.');

-- Açougue
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, img_url, descricao_produto) VALUES
('Carne Bovina 1kg', 35.00, 100, '2025-11-30', 5, 'https://example.com/carne-bovina.png', 'Carne bovina fresca e selecionada.');

-- Higiene
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, img_url, descricao_produto) VALUES
('Papel Higiênico Neve 12 rolos', 18.99, 180, '2028-03-10', 6, 'https://m.media-amazon.com/images/I/71X9mMJ6ATL._UF1000,1000_QL80_.jpg', 'Papel higiênico macio e resistente.');

-- Padaria
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, img_url, descricao_produto) VALUES
('Pão Francês 1kg', 12.00, 150, '2025-11-25', 7, 'https://example.com/pao-frances.png', 'Pão francês fresquinho e crocante.');

-- Pet Shop
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, img_url, descricao_produto) VALUES
('Ração Golden 1kg', 25.00, 200, '2026-10-10', 8, 'https://example.com/racao-golden.png', 'Ração completa para cães.');

-- ======================================
-- PEDIDOS DE CLIENTE
-- ======================================
INSERT INTO tb_pedido_usuario (
  id_pedido_usuario,
  valor_total,
  status_pedido,
  data_pedido,
  id_usuario,
  id_Funcionario
)
VALUES (1 , 33.40, 'ENTREGUE', '2025-09-30', 1, 1);

-- ======================================
-- PEDIDOS DE FORNECEDOR
-- ======================================
INSERT INTO tb_pedido_fornecedor (data_Pedido_Fornecedor, id_Produto, id_Fornecedor)
VALUES ('2025-09-25', 3, 1);
