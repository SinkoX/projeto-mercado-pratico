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
-- ENDEREÇOS
-- ======================================
INSERT INTO tb_endereco (cep, rua, numero, bairro, cidade, estado, complemento, id_usuario) VALUES
('18010-000', 'Rua XV de Novembro', '123', 'Centro', 'Sorocaba', 'SP', 'Apartamento 45', 1),
('18020-050', 'Avenida São Paulo', '987', 'Jardim Paulista', 'Sorocaba', 'SP', 'Casa 2', 2);


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
INSERT INTO tb_produto (nome_Produto, preco_Produto, quantidade, data_Validade, id_Subcategoria, img_url) VALUES
('Arroz 10kg', 49.90, 120, '2026-12-01', 1, "https://superprix.vteximg.com.br/arquivos/ids/174487/Arroz-Tio-Joao-Branco-1kg.png?v=636209529502870000"),
('Feijão Carioca 5kg', 25.00, 150, '2026-07-20', 2, "https://kicaldo.com.br/wp-content/uploads/2020/07/Kicaldo-feijaocarioca.png"),
('Macarrão Penne', 6.99, 180, '2026-04-15', 3, "https://vitarella.com.br/wp-content/uploads/2020/11/speciale_Semola_penne_400g-792x792-1.png"),
('Macarrão para Lasanha', 7.50, 160, '2026-05-10', 3, "https://gbarbosa.vtexassets.com/arquivos/ids/210932/655265fd8d0743e14888e9d2.jpg?v=638354956818570000"),
('Óleo de Soja 900ml', 4.50, 200, '2027-03-15', 2, "https://mercantilnovaera.vtexassets.com/arquivos/ids/214695/Oleo-de-Soja-SOYA-Garrafa-900ml.jpg?v=638447417992900000"),
('Detergente Ypê 500ml', 1.99, 220, '2027-06-30', 5, "https://castronaves.vteximg.com.br/arquivos/ids/371251-1000-1000/84574_01.jpg?v=637655863428400000"),
('Coca-Cola Lata 350ml', 3.20, 300, '2026-08-20', 6, "https://superprix.vteximg.com.br/arquivos/ids/210608-600-600/BMo6Zlso.png?v=638083543189830000"),
('Fanta Uva 2L', 6.50, 250, '2026-07-10', 6, "https://superprix.vteximg.com.br/arquivos/ids/226361-600-600/Refrigerante-Fanta-Uva-2l.png?v=638774016807230000"),
('Sabão em Pó Omo 1kg', 12.90, 180, '2027-01-05', 4, "https://cdn-cosmos.bluesoft.com.br/products/7891150018587"),
('Desinfetante Pinho Sol 500ml', 3.80, 230, '2027-09-01', 5, "https://superprix.vteximg.com.br/arquivos/ids/176738/Desinfetante-Pinho-Sol-Lavanda-500ml.png?v=636495363697370000"),
('Refrigerante Guaraná Antártica 2L', 7.90, 180, '2026-10-10', 6, "https://supermercadobomdemais.com.br/wp-content/uploads/2020/05/Refrigerante-Guaran%C3%A1-Antarctica-2l.png"),
('Água Mineral 1,5L', 2.99, 300, '2027-11-30', 3, "https://cdn.awsli.com.br/446/446822/produto/231083650/shopping---2023-08-28t130846-088-b17cy747kz.jpg"),
('Leite Integral 1L', 4.20, 250, '2026-12-15', 2, "https://piracanjuba-institucional-prd.s3.sa-east-1.amazonaws.com/product_images/image/leite-piracanjuba-integral-1l-frente-848x1007px-460.webp"),
('Queijo Mussarela 500g', 22.50, 100, '2026-06-01', 1, "https://piracanjuba-institucional-prd.s3.sa-east-1.amazonaws.com/product_images/image/queijo-mussarela-pedaco-piracanjuba-500g-848x1007px-626.webp"),
('Cereal Sucrilhos Kelloggs 300g', 12.90, 150, '2026-09-20', 3, "https://carrefourbr.vtexassets.com/arquivos/ids/182933355/image-0.jpg?v=638732574875630000"),
('Papel Higiênico Neve 12 rolos', 18.99, 180, '2028-03-10', 5, "https://m.media-amazon.com/images/I/71X9mMJ6ATL._UF1000,1000_QL80_.jpg");


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
