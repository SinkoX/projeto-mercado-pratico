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
INSERT INTO tb_endereco (cep, rua, numero, bairro, cidade, estado, complemento, id_usuario) VALUES
('18010-000', 'Rua XV de Novembro', '123', 'Centro', 'Sorocaba', 'SP', 'Apartamento 45', 1),
('18020-050', 'Avenida São Paulo', '987', 'Jardim Paulista', 'Sorocaba', 'SP', 'Casa 2', 2);

-- ======================================
-- FUNCIONÁRIOS
-- ======================================
INSERT INTO tb_funcionario (nome_Funcionario, email_Funcionario, senha_Funcionario, cpf_Funcionario, telefone_funcionario, cargo, id_Tipo_Usuario) VALUES
('PzKing', 'pzKing@mercado.com', 'pzking123', '11122233344', '15 972814010', 'Atendente', 3);

-- ======================================
-- FORNECEDORES
-- ======================================
INSERT INTO tb_fornecedor (nome_Fornecedor, email_Fornecedor, cpf_Fornecedor, telefone_fornecedor, cnpj) VALUES
('Fornecedor A', 'fornecedorA@email.com', '22233344455', '15 999282837', '12345678000199'),
('Fornecedor B', 'fornecedorB@email.com', '33344455566', '15 981725747', '98765432000188');

-- ======================================
-- CATEGORIAS
-- ======================================
INSERT INTO tb_categoria (nome_Categoria) VALUES
('Hortifruti'),
('Bebidas'),
('Mercearia'),
('Limpeza'),
('Açougue'),
('Higiene'),
('Padaria'),
('Pet Shop');

-- ======================================
-- SUBCATEGORIAS
-- ======================================
INSERT INTO tb_subcategoria (nome_Subcategoria, id_Categoria) VALUES
('Frutas', 1),         -- Hortifruti
('Verduras', 1),
('Refrigerantes', 2),  -- Bebidas
('Sucos', 2),
('Arroz', 3),          -- Mercearia
('Feijão', 3),
('Detergentes', 4),    -- Limpeza
('Sabão em pó', 4),
('Carnes', 5),         -- Açougue
('Papel Higiênico', 6),-- Higiene
('Pães', 7),           -- Padaria
('Rações', 8);         -- Pet Shop

-- ======================================
-- PRODUTOS
-- ======================================
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_subcategoria, img_url, descricao_produto) VALUES
('Arroz 10kg', 49.90, 120, '2026-12-01', 5, 'https://superprix.vteximg.com.br/arquivos/ids/174487/Arroz-Tio-Joao-Branco-1kg.png', 'Arroz tipo 1 de qualidade premium, grãos longos e soltinhos.'),
('Feijão Carioca 5kg', 25.00, 150, '2026-07-20', 5, 'https://kicaldo.com.br/wp-content/uploads/2020/07/Kicaldo-feijaocarioca.png', 'Feijão carioca selecionado, grãos firmes e sabor marcante.'),
('Macarrão Penne', 6.99, 180, '2026-04-15', 6, 'https://vitarella.com.br/wp-content/uploads/2020/11/speciale_Semola_penne_400g-792x792-1.png', 'Macarrão tipo penne de alta qualidade.'),
('Macarrão para Lasanha', 7.50, 160, '2026-05-10', 6, 'https://gbarbosa.vtexassets.com/arquivos/ids/210932/655265fd8d0743e14888e9d2.jpg', 'Massa para lasanha pré-cozida e prática.'),
('Óleo de Soja 900ml', 4.50, 200, '2027-03-15', 6, 'https://mercantilnovaera.vtexassets.com/arquivos/ids/214695/Oleo-de-Soja-SOYA-Garrafa-900ml.jpg', 'Óleo de soja 100% puro, ideal para frituras.'),
('Detergente Ypê 500ml', 1.99, 220, '2027-06-30', 7, 'https://castronaves.vteximg.com.br/arquivos/ids/371251-1000-1000/84574_01.jpg', 'Detergente Ypê com fórmula concentrada.'),
('Coca-Cola Lata 350ml', 3.20, 300, '2026-08-20', 3, 'https://superprix.vteximg.com.br/arquivos/ids/210608-600-600/BMo6Zlso.png', 'Coca-Cola Original em lata.'),
('Fanta Uva 2L', 6.50, 250, '2026-07-10', 3, 'https://superprix.vteximg.com.br/arquivos/ids/226361-600-600/Refrigerante-Fanta-Uva-2l.png', 'Refrigerante Fanta sabor uva.'),
('Sabão em Pó Omo 1kg', 12.90, 180, '2027-01-05', 8, 'https://cdn-cosmos.bluesoft.com.br/products/7891150018587', 'Sabão em pó Omo Multiação.'),
('Desinfetante Pinho Sol 500ml', 3.80, 230, '2027-09-01', 5, 'https://superprix.vteximg.com.br/arquivos/ids/176738/Desinfetante-Pinho-Sol-Lavanda-500ml.png', 'Desinfetante Pinho Sol com fragrância marcante.'),
('Refrigerante Guaraná Antártica 2L', 7.90, 180, '2026-10-10', 3, 'https://supermercadobomdemais.com.br/wp-content/uploads/2020/05/Refrigerante-Guaran%C3%A1-Antarctica-2l.png', 'Guaraná Antártica, sabor do Brasil.'),
('Água Mineral 1,5L', 2.99, 300, '2027-11-30', 5, 'https://cdn.awsli.com.br/446/446822/produto/231083650/shopping---2023-08-28t130846-088-b17cy747kz.jpg', 'Água mineral natural, pura e leve.'),
('Leite Integral 1L', 4.20, 250, '2026-12-15', 2, 'https://piracanjuba-institucional-prd.s3.sa-east-1.amazonaws.com/product_images/image/leite-piracanjuba-integral-1l-frente-848x1007px-460.webp', 'Leite integral Piracanjuba, fonte de cálcio e sabor cremoso.'),
('Queijo Mussarela 500g', 22.50, 100, '2026-06-01', 1, 'https://piracanjuba-institucional-prd.s3.sa-east-1.amazonaws.com/product_images/image/queijo-mussarela-pedaco-piracanjuba-500g-848x1007px-626.webp', 'Queijo mussarela fresco e saboroso.'),
('Cereal Sucrilhos Kelloggs 300g', 12.90, 150, '2026-09-20', 3, 'https://carrefourbr.vtexassets.com/arquivos/ids/182933355/image-0.jpg', 'Cereal matinal Kelloggs Sucrilhos, crocante e saboroso.'),
('Papel Higiênico Neve 12 rolos', 18.99, 180, '2028-03-10', 5, 'https://m.media-amazon.com/images/I/71X9mMJ6ATL._UF1000,1000_QL80_.jpg', 'Papel higiênico Neve com textura macia e resistente.');

-- ======================================
-- PEDIDOS DE CLIENTE
-- ======================================
INSERT INTO tb_pedido_usuario (
  id_carrinho,
  valor_total,
  status_pedido_usuario,
  data_pedido_usuario,
  id_Usuario,
  id_Funcionario
)
VALUES (NULL, 33.40, 'Finalizado', '2025-09-30', 1, 1);

-- ======================================
-- PEDIDOS DE FORNECEDOR
-- ======================================
INSERT INTO tb_pedido_fornecedor (data_Pedido_Fornecedor, id_Produto, id_Fornecedor)
VALUES ('2025-09-25', 3, 1);
