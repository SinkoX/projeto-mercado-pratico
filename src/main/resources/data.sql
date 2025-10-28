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
-- HORTIFRUTI (1)
('Frutas', 1),
('Verduras', 1),
('Legumes', 1),
('Temperos e Ervas', 1),
('Orgânicos', 1),
('Grãos e Raízes', 1),

-- BEBIDAS (2)
('Refrigerantes', 2),
('Sucos', 2),
('Águas', 2),
('Cervejas', 2),
('Vinhos', 2),
('Destilados', 2),
('Energéticos', 2),
('Leites e Derivados', 2),

-- MERCEARIA (3)
('Arroz e Feijão', 3),
('Massas', 3),
('Farinhas e Cereais', 3),
('Enlatados e Conservas', 3),
('Molhos e Temperos', 3),
('Óleos e Gorduras', 3),
('Açúcar e Café', 3),
('Biscoitos e Snacks', 3),

-- LIMPEZA (4)
('Limpeza Doméstica', 4),
('Lavanderia', 4),
('Higiene do Lar', 4),
('Inseticidas', 4),
('Descartáveis', 4),

-- AÇOUGUE (5)
('Carnes Bovinas', 5),
('Carnes Suínas', 5),
('Aves', 5),
('Peixes e Frutos do Mar', 5),
('Embutidos', 5),

-- HIGIENE (6)
('Cabelos', 6),
('Corpo e Banho', 6),
('Higiene Oral', 6),
('Cuidados Pessoais', 6),
('Barbear e Depilação', 6),

-- PADARIA (7)
('Pães', 7),
('Bolos e Tortas', 7),
('Salgados', 7),
('Doces', 7),
('Biscoitos Artesanais', 7),
('Produtos Integrais', 7),

-- PETSHOP (8)
('Ração', 8),
('Petiscos', 8),
('Brinquedos', 8),
('Higiene e Cuidados', 8),
('Acessórios', 8),
('Saúde e Medicamentos', 8);


-- ======================================
-- PRODUTOS
-- ======================================
-- HORTIFRUTI
INSERT INTO tb_produto 
(nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES

-- FRUTAS (id_subcategoria = 1)
('Maçã Fuji 1kg', 7.90, 150, '2025-11-15', 1, 1, 'https://obahortifruti.vtexassets.com/arquivos/ids/4250277/Maca-Fuji.jpg?v=638144112304430000', 'Maçãs Fuji frescas, crocantes e doces.'),
('Banana Nanica 1kg', 5.20, 200, '2025-11-10', 1, 1, 'https://vallefrutas.com.br/wp-content/uploads/banana-nanica.png', 'Bananas maduras e ideais para o dia a dia.'),

-- VERDURAS (id_subcategoria = 2)
('Alface Crespa Unidade', 3.50, 100, '2025-11-05', 1, 2, 'https://obahortifruti.vtexassets.com/arquivos/ids/4264300/Alface-Crespa-Hidroponica-Oba-Bem-Querer-Unidade.jpg?v=638144122123830000', 'Alface fresca, cultivada sem agrotóxicos.'),
('Espinafre Maço', 4.80, 80, '2025-11-07', 1, 2, 'https://redemix.vteximg.com.br/arquivos/ids/205915-1000-1000/32.jpg?v=638350587170270000', 'Espinafre fresco e rico em ferro.'),

-- LEGUMES (id_subcategoria = 3)
('Cenoura 1kg', 4.30, 120, '2025-11-20', 1, 3, 'https://www.bernardaoemcasa.com.br/media/catalog/product/cache/1/image/500x500/9df78eab33525d08d6e5fb8d27136e95/c/n/cn001-cenoura.jpg', 'Cenouras selecionadas e crocantes.'),
('Tomate Italiano 1kg', 6.90, 150, '2025-11-18', 1, 3, 'https://zaffari.vtexassets.com/arquivos/ids/290600/1007601-00.jpg?v=638969319392730000', 'Tomates italianos ideais para molhos e saladas.'),

-- TEMPEROS E ERVAS (id_subcategoria = 4)
('Salsinha Maço', 2.50, 60, '2025-11-08', 1, 4, 'https://giassi.vtexassets.com/arquivos/ids/2756956/Salsinha-Maco.png?v=638627544776070000', 'Salsinha fresca para temperar suas receitas.'),
('Manjericão Bandeja 30g', 4.20, 50, '2025-11-09', 1, 4, 'https://meufestval.vtexassets.com/arquivos/ids/182685-800-800?v=637731908959400000&width=800&height=800&aspect=true', 'Manjericão fresco com aroma intenso.'),

-- ORGÂNICOS (id_subcategoria = 5)
('Tomate Orgânico 500g', 7.50, 80, '2025-11-15', 1, 5, 'https://www.hortifrutiorganico.com.br/24-large_default/tomate-italiano-organico-para-molho-1-kg.jpg', 'Tomates cultivados sem agrotóxicos.'),
('Alface Americana Orgânica Unidade', 6.20, 70, '2025-11-12', 1, 5, 'https://mercadoorganico.com/6324-large_default/alface-americana-organica-un-osm.jpg', 'Alface orgânica crocante e saudável.'),

-- GRÃOS E RAÍZES (id_subcategoria = 6)
('Batata Inglesa 1kg', 5.10, 200, '2025-11-25', 1, 6, 'https://www.hortifruti.com.br/_next/image?url=https%3A%2F%2Fhortifrutibr.vtexassets.com%2Farquivos%2Fids%2F156865%2FBatata-Inglesa-Organica-Natural-Da-Terra.jpg%3Fv%3D638784359761670000%26format%3Dwebp&w=1440&q=75', 'Batatas selecionadas, ideais para fritar ou cozinhar.'),
('Beterraba 1kg', 4.60, 100, '2025-11-22', 1, 6, 'https://mercadoorganico.com/6580-large_default/beterraba-organica-500g-osm.jpg', 'Beterrabas frescas e adocicadas.');


-- BEBIDAS
INSERT INTO tb_produto 
(nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES

-- REFRIGERANTES (id_subcategoria = 7)
('Coca-Cola Lata 350ml', 3.20, 300, '2026-08-20', 2, 7, 'https://superprix.vteximg.com.br/arquivos/ids/210608-600-600/BMo6Zlso.png', 'Coca-Cola Original em lata.'),
('Guaraná Antarctica 2L', 8.50, 180, '2026-07-30', 2, 7, 'https://superprix.vteximg.com.br/arquivos/ids/210610-600-600/guarana-antarctica-2l.png', 'Refrigerante Guaraná Antarctica 2 litros.'),

-- SUCOS (id_subcategoria = 8)
('Suco de Laranja Natural One 1L', 9.90, 120, '2026-02-15', 2, 8, 'https://superprix.vteximg.com.br/arquivos/ids/210615-600-600/suco-laranja-natural-one-1l.png', 'Suco 100% natural, sem conservantes.'),
('Suco de Uva Integral Aurora 1,5L', 14.90, 90, '2026-03-01', 2, 8, 'https://superprix.vteximg.com.br/arquivos/ids/210614-600-600/suco-uva-aurora-1-5l.png', 'Suco integral de uva Aurora, sem adição de açúcar.'),

-- ÁGUAS (id_subcategoria = 9)
('Água Mineral Crystal Sem Gás 500ml', 2.00, 400, '2028-05-10', 2, 9, 'https://superprix.vteximg.com.br/arquivos/ids/210611-600-600/agua-crystal-500ml.png', 'Água mineral natural Crystal, sem gás.'),
('Água Mineral com Gás São Lourenço 510ml', 3.50, 250, '2028-05-10', 2, 9, 'https://superprix.vteximg.com.br/arquivos/ids/210612-600-600/agua-sao-lourenco-510ml.png', 'Água gaseificada leve e refrescante.'),

-- CERVEJAS (id_subcategoria = 10)
('Cerveja Heineken Long Neck 330ml', 6.90, 180, '2026-04-15', 2, 10, 'https://superprix.vteximg.com.br/arquivos/ids/210613-600-600/heineken-330ml.png', 'Cerveja puro malte Heineken long neck.'),
('Cerveja Budweiser Lata 350ml', 5.80, 200, '2026-04-20', 2, 10, 'https://superprix.vteximg.com.br/arquivos/ids/210609-600-600/budweiser-350ml.png', 'Cerveja Budweiser tradicional em lata.'),

-- VINHOS (id_subcategoria = 11)
('Vinho Tinto Seco Miolo Reserva 750ml', 49.90, 50, '2030-12-01', 2, 11, 'https://superprix.vteximg.com.br/arquivos/ids/210616-600-600/vinho-miolo-reserva.png', 'Vinho tinto seco Miolo Reserva Cabernet Sauvignon.'),
('Vinho Branco Suave Salton 750ml', 39.90, 60, '2030-12-01', 2, 11, 'https://superprix.vteximg.com.br/arquivos/ids/210617-600-600/vinho-salton-suave.png', 'Vinho branco suave Salton, sabor leve e frutado.'),

-- DESTILADOS (id_subcategoria = 12)
('Vodka Smirnoff 998ml', 41.90, 80, '2032-06-01', 2, 12, 'https://superprix.vteximg.com.br/arquivos/ids/210618-600-600/vodka-smirnoff-998ml.png', 'Vodka Smirnoff tripla destilação.'),
('Whisky Johnnie Walker Red Label 1L', 129.90, 40, '2035-08-01', 2, 12, 'https://superprix.vteximg.com.br/arquivos/ids/210619-600-600/johnnie-walker-red-label.png', 'Whisky escocês de sabor marcante.'),

-- ENERGÉTICOS (id_subcategoria = 13)
('Energético Red Bull Lata 250ml', 9.50, 300, '2026-01-01', 2, 13, 'https://superprix.vteximg.com.br/arquivos/ids/210620-600-600/red-bull-250ml.png', 'Energético Red Bull, te dá asas.'),
('Energético Monster 473ml', 11.90, 250, '2026-01-01', 2, 13, 'https://superprix.vteximg.com.br/arquivos/ids/210621-600-600/monster-473ml.png', 'Energético Monster Energy tradicional.'),

-- LEITES E DERIVADOS (id_subcategoria = 14)
('Leite Integral Itambé 1L', 5.40, 300, '2025-12-15', 2, 14, 'https://superprix.vteximg.com.br/arquivos/ids/210622-600-600/leite-itambe-1l.png', 'Leite integral Itambé, fonte de cálcio.'),
('Leite Desnatado Piracanjuba 1L', 5.20, 280, '2025-12-15', 2, 14, 'https://superprix.vteximg.com.br/arquivos/ids/210623-600-600/leite-piracanjuba-desnatado.png', 'Leite desnatado Piracanjuba, leve e nutritivo.');

-- MERCEARIA
INSERT INTO tb_produto 
(nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES

-- ARROZ E FEIJÃO (id_subcategoria = 15)
('Arroz Tio João Tipo 1 5kg', 24.90, 200, '2026-08-10', 3, 15, 'https://superprix.vteximg.com.br/arquivos/ids/210700-600-600/arroz-tio-joao-5kg.png', 'Arroz branco tipo 1, grãos selecionados.'),
('Feijão Carioca Camil 1kg', 9.80, 180, '2026-05-05', 3, 15, 'https://superprix.vteximg.com.br/arquivos/ids/210701-600-600/feijao-camil-1kg.png', 'Feijão carioca de alta qualidade, sabor tradicional.'),

-- MASSAS (id_subcategoria = 16)
('Macarrão Espaguete Renata 500g', 5.90, 160, '2026-06-15', 3, 16, 'https://superprix.vteximg.com.br/arquivos/ids/210702-600-600/macarrao-renata-espaguete.png', 'Macarrão espaguete Renata, cozimento rápido e textura ideal.'),
('Macarrão Parafuso Adria 500g', 5.70, 140, '2026-07-01', 3, 16, 'https://superprix.vteximg.com.br/arquivos/ids/210703-600-600/macarrao-parafuso-adria.png', 'Macarrão tipo parafuso Adria, ideal para saladas e molhos.'),

-- FARINHAS E CEREAIS (id_subcategoria = 17)
('Farinha de Trigo Dona Benta 1kg', 6.40, 120, '2026-09-01', 3, 17, 'https://superprix.vteximg.com.br/arquivos/ids/210704-600-600/farinha-dona-benta-1kg.png', 'Farinha de trigo tipo 1 para bolos e pães.'),
('Aveia em Flocos Finos Quaker 170g', 4.90, 100, '2026-04-01', 3, 17, 'https://superprix.vteximg.com.br/arquivos/ids/210705-600-600/aveia-quaker-finos.png', 'Aveia em flocos finos Quaker, ideal para vitaminas e mingaus.'),

-- ENLATADOS E CONSERVAS (id_subcategoria = 18)
('Milho Verde Quero 200g', 3.50, 150, '2027-01-15', 3, 18, 'https://superprix.vteximg.com.br/arquivos/ids/210706-600-600/milho-verde-quero.png', 'Milho verde em conserva, pronto para consumo.'),
('Ervilha Predilecta 200g', 3.20, 130, '2027-01-10', 3, 18, 'https://superprix.vteximg.com.br/arquivos/ids/210707-600-600/ervilha-predilecta.png', 'Ervilhas macias e saborosas em conserva.'),

-- MOLHOS E TEMPEROS (id_subcategoria = 19)
('Molho de Tomate Pomarola Tradicional 340g', 4.60, 180, '2027-02-01', 3, 19, 'https://superprix.vteximg.com.br/arquivos/ids/210708-600-600/molho-pomarola.png', 'Molho de tomate Pomarola com sabor caseiro.'),
('Tempero Sazón Alho e Sal 60g', 7.30, 200, '2027-05-01', 3, 19, 'https://superprix.vteximg.com.br/arquivos/ids/210709-600-600/tempero-sazon-alho-sal.png', 'Tempero prático para realçar o sabor dos pratos.'),

-- ÓLEOS E GORDURAS (id_subcategoria = 20)
('Óleo de Soja Liza 900ml', 8.90, 180, '2026-11-01', 3, 20, 'https://superprix.vteximg.com.br/arquivos/ids/210710-600-600/oleo-liza.png', 'Óleo de soja refinado, ideal para frituras e refogados.'),
('Azeite Gallo Extra Virgem 500ml', 29.90, 100, '2028-03-01', 3, 20, 'https://superprix.vteximg.com.br/arquivos/ids/210711-600-600/azeite-gallo-500ml.png', 'Azeite extra virgem Gallo, sabor intenso e frutado.'),

-- AÇÚCAR E CAFÉ (id_subcategoria = 21)
('Açúcar Refinado União 1kg', 4.20, 250, '2026-08-01', 3, 21, 'https://superprix.vteximg.com.br/arquivos/ids/210712-600-600/acucar-uniao-1kg.png', 'Açúcar refinado União, ideal para receitas e bebidas.'),
('Café Pilão Tradicional 500g', 17.90, 150, '2026-12-01', 3, 21, 'https://superprix.vteximg.com.br/arquivos/ids/210713-600-600/cafe-pilao-500g.png', 'Café torrado e moído Pilão, sabor encorpado e aroma marcante.'),

-- BISCOITOS E SNACKS (id_subcategoria = 22)
('Biscoito Trakinas Chocolate 126g', 3.90, 200, '2026-03-01', 3, 22, 'https://superprix.vteximg.com.br/arquivos/ids/210714-600-600/trakinas-chocolate.png', 'Biscoito recheado Trakinas sabor chocolate.'),
('Batata Ruffles Original 96g', 9.50, 180, '2026-05-01', 3, 22, 'https://superprix.vteximg.com.br/arquivos/ids/210715-600-600/ruffles-original.png', 'Batata chips Ruffles sabor original, crocante e saborosa.');
	
-- LIMPEZA
INSERT INTO tb_produto 
(nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES

-- LIMPEZA DOMÉSTICA (id_subcategoria = 23)
('Desinfetante Pinho Sol 500ml', 7.90, 150, '2027-06-01', 4, 23, 'https://superprix.vteximg.com.br/arquivos/ids/210730-600-600/pinho-sol-500ml.png', 'Desinfetante concentrado com aroma de pinho.'),
('Multiuso Veja Original 500ml', 8.20, 200, '2027-06-01', 4, 23, 'https://superprix.vteximg.com.br/arquivos/ids/210731-600-600/veja-multiuso.png', 'Limpador multiuso Veja, ideal para superfícies diversas.'),

-- LAVANDERIA (id_subcategoria = 24)
('Sabão em Pó Omo Lavagem Perfeita 1,6kg', 24.90, 120, '2027-03-01', 4, 24, 'https://superprix.vteximg.com.br/arquivos/ids/210732-600-600/sabao-omo-16kg.png', 'Sabão em pó Omo para roupas mais limpas e perfumadas.'),
('Amaciante Downy Brisa de Verão 1L', 12.50, 140, '2027-04-01', 4, 24, 'https://superprix.vteximg.com.br/arquivos/ids/210733-600-600/amaciante-downy-1l.png', 'Amaciante Downy com perfume duradouro e maciez.'),

-- HIGIENE DO LAR (id_subcategoria = 25)
('Esponja de Limpeza Scotch-Brite Leve 4 Pague 3', 8.90, 250, '2028-01-01', 4, 25, 'https://superprix.vteximg.com.br/arquivos/ids/210734-600-600/esponja-scotch-brite.png', 'Esponjas resistentes, ideais para uso diário na cozinha.'),
('Pano de Chão Bettanin 60x70cm', 6.50, 180, '2028-01-01', 4, 25, 'https://superprix.vteximg.com.br/arquivos/ids/210735-600-600/pano-de-chao-bettanin.png', 'Pano de chão de algodão com alta absorção.'),

-- INSETICIDAS (id_subcategoria = 26)
('Inseticida Raid Multi Insetos 420ml', 15.90, 160, '2028-07-01', 4, 26, 'https://superprix.vteximg.com.br/arquivos/ids/210736-600-600/raid-multi-insetos.png', 'Inseticida aerossol para eliminar mosquitos e baratas.'),
('Inseticida SBP Elétrico Refil 45 Noites', 18.50, 120, '2028-07-01', 4, 26, 'https://superprix.vteximg.com.br/arquivos/ids/210737-600-600/sbp-refil.png', 'Refil SBP elétrico com ação prolongada contra mosquitos.'),

-- DESCARTÁVEIS (id_subcategoria = 27)
('Sacos de Lixo 50L C/10 Unidades', 10.90, 200, '2029-01-01', 4, 27, 'https://superprix.vteximg.com.br/arquivos/ids/210738-600-600/saco-de-lixo-50l.png', 'Sacos de lixo resistentes, ideais para uso doméstico.'),
('Luvas de Limpeza Scotch-Brite Tamanho M', 8.90, 150, '2029-01-01', 4, 27, 'https://superprix.vteximg.com.br/arquivos/ids/210739-600-600/luvas-scotch-brite.png', 'Luvas duráveis e confortáveis, ideais para tarefas domésticas.');

-- AÇOUGUE
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES
-- CARNES BOVINAS (id_subcategoria = 28)
('Picanha Bovina 1kg', 69.90, 40, '2025-12-05', 5, 28, 'https://static.paodeacucar.com/img/uploads/1/410/628410.jpg', 'Corte nobre de picanha bovina, ideal para churrascos.'),
('Carne Moída de Patinho 500g', 17.90, 70, '2025-11-30', 5, 28, 'https://superprix.vteximg.com.br/arquivos/ids/206661-600-600/carne-moida-de-patinho-bovino-congelado-kg.png', 'Carne bovina moída fresca, ideal para molhos e recheios.'),

-- CARNES SUÍNAS (id_subcategoria = 29)
('Bisteca Suína 1kg', 19.90, 60, '2025-11-18', 5, 29, 'https://superprix.vteximg.com.br/arquivos/ids/207780-600-600/bisteca-suina-congelada-kg.png', 'Bisteca suína fresca, ótima para grelhar ou fritar.'),
('Costelinha Suína 1kg', 24.90, 50, '2025-11-20', 5, 29, 'https://static.clubeextra.com.br/img/uploads/1/600/410600.png', 'Costelinha suína selecionada, ideal para forno ou churrasco.'),

-- AVES (id_subcategoria = 30)
('Filé de Peito de Frango Congelado 1kg', 14.90, 100, '2025-12-10', 5, 30, 'https://mercado.carrefour.com.br/medias/sys_master/images/images/h1e/hf3/14017869303838.jpg', 'Filé de peito de frango congelado, pronto para preparo.'),
('Coxa e Sobrecoxa de Frango 1kg', 12.50, 80, '2025-11-25', 5, 30, 'https://static.paodeacucar.com/img/uploads/1/864/536864.jpg', 'Corte de frango com pele e osso, ideal para assar ou fritar.'),

-- PEIXES (id_subcategoria = 31)
('Filé de Tilápia Congelado 500g', 27.90, 60, '2025-12-20', 5, 31, 'https://superprix.vteximg.com.br/arquivos/ids/210477-600-600/file-de-tilapia-congelado-kg.png', 'Filé de tilápia sem espinhas, leve e saboroso.'),
('Camarão Cinza Médio 400g', 39.90, 50, '2025-12-30', 5, 31, 'https://superprix.vteximg.com.br/arquivos/ids/210720-600-600/camarao-cinza-medio-congelado-kg.png', 'Camarão médio congelado, ideal para moquecas e risotos.'),

-- EMBUTIDOS (id_subcategoria = 32)
('Linguiça Toscana 1kg', 22.50, 90, '2026-01-10', 5, 32, 'https://superprix.vteximg.com.br/arquivos/ids/207793-600-600/linguica-toscana-congelada-kg.png', 'Linguiça toscana tradicional, ideal para grelhar.'),
('Bacon Fatiado 250g', 14.50, 75, '2025-12-01', 5, 32, 'https://superprix.vteximg.com.br/arquivos/ids/207794-600-600/bacon-fatiado-kg.png', 'Bacon defumado fatiado, perfeito para lanches e receitas.');


-- HIGIENE
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES
-- CABELOS (id_subcategoria = 33)
('Shampoo Seda Hidratação 325ml', 9.90, 120, '2027-05-15', 6, 33, 'https://drogariaspacheco.vteximg.com.br/arquivos/ids/660001-600-600/7891150049772.jpg', 'Shampoo com fórmula hidratante, ideal para cabelos normais e secos.'),
('Condicionador Dove Nutrição 200ml', 12.50, 100, '2027-06-10', 6, 33, 'https://drogasil.vteximg.com.br/arquivos/ids/560563-600-600/condicionador-dove-nutricao-200ml.png', 'Condicionador Dove para cabelos nutridos e macios.'),

-- CORPO E BANHO (id_subcategoria = 34)
('Sabonete Dove Original 90g', 3.50, 300, '2027-02-20', 6, 34, 'https://drogasil.vteximg.com.br/arquivos/ids/390503-600-600/sabonete-dove-original-90g.png', 'Sabonete hidratante com ¼ de creme, ideal para pele macia.'),
('Sabonete Líquido Nivea Erva Doce 250ml', 14.90, 80, '2027-03-15', 6, 34, 'https://cdn-cosmos.bluesoft.com.br/products/7894000945679', 'Sabonete líquido corporal com fragrância suave e refrescante.'),

-- HIGIENE ORAL (id_subcategoria = 35)
('Creme Dental Colgate Total 90g', 8.90, 200, '2027-04-30', 6, 35, 'https://drogariaspacheco.vteximg.com.br/arquivos/ids/744048-600-600/creme-dental-colgate-total-90g.jpg', 'Creme dental antibacteriano que protege dentes e gengivas.'),
('Enxaguante Bucal Listerine Cool Mint 500ml', 22.90, 100, '2027-06-20', 6, 35, 'https://drogasil.vteximg.com.br/arquivos/ids/514621-600-600/enxaguante-bucal-listerine-500ml.png', 'Enxaguante bucal com ação antibacteriana e hálito refrescante.'),

-- CUIDADOS PESSOAIS (id_subcategoria = 36)
('Desodorante Rexona Men Aerosol 150ml', 15.90, 150, '2027-07-10', 6, 36, 'https://drogariaspacheco.vteximg.com.br/arquivos/ids/684735-600-600/7891150075139.jpg', 'Desodorante masculino com 48h de proteção e fragrância marcante.'),
('Hidratante Corporal Nivea Milk 200ml', 19.90, 90, '2027-08-05', 6, 36, 'https://drogasil.vteximg.com.br/arquivos/ids/515640-600-600/hidratante-nivea-milk-200ml.png', 'Hidratante corporal com óleo de amêndoas, ideal para pele seca.'),

-- BARBEAR E DEPILAÇÃO (id_subcategoria = 37)
('Aparelho de Barbear Gillette Mach3', 39.90, 70, '2028-01-10', 6, 37, 'https://drogasil.vteximg.com.br/arquivos/ids/535050-600-600/aparelho-de-barbear-gillette-mach3.png', 'Aparelho de barbear com três lâminas e cabo ergonômico.'),
('Creme Depilatório Veet Corporal 100g', 24.90, 60, '2027-09-15', 6, 37, 'https://drogariaspacheco.vteximg.com.br/arquivos/ids/715017-600-600/7894000631046.jpg', 'Creme depilatório para peles sensíveis, fácil e rápido de usar.');

-- PADAROA
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES
-- PÃES (id_subcategoria = 38)
('Pão Francês 50g', 0.80, 500, '2025-10-30', 7, 38, 'https://superprix.vteximg.com.br/arquivos/ids/208776-600-600/pao-frances-kg.png', 'Pão francês crocante por fora e macio por dentro, assado diariamente.'),
('Pão de Forma Integral 500g', 8.90, 120, '2025-12-10', 7, 38, 'https://drogariaspacheco.vteximg.com.br/arquivos/ids/620821-600-600/pao-de-forma-integral-500g.jpg', 'Pão de forma integral, rico em fibras e ideal para o café da manhã.'),

-- BOLOS E TORTAS (id_subcategoria = 39)
('Bolo de Chocolate 500g', 16.90, 40, '2025-11-05', 7, 39, 'https://superprix.vteximg.com.br/arquivos/ids/210601-600-600/bolo-chocolate.png', 'Bolo de chocolate caseiro com cobertura cremosa.'),
('Torta de Limão 600g', 22.90, 30, '2025-11-08', 7, 39, 'https://superprix.vteximg.com.br/arquivos/ids/211230-600-600/torta-de-limao.png', 'Torta artesanal de limão com base crocante e merengue suave.'),

-- SALGADOS (id_subcategoria = 40)
('Coxinha de Frango 100g', 4.50, 200, '2025-11-01', 7, 40, 'https://superprix.vteximg.com.br/arquivos/ids/209654-600-600/coxinha-de-frango.png', 'Coxinha de frango tradicional, massa leve e recheio cremoso.'),
('Empada de Palmito 90g', 5.20, 180, '2025-11-02', 7, 40, 'https://superprix.vteximg.com.br/arquivos/ids/209655-600-600/empada-de-palmito.png', 'Empada artesanal recheada com palmito temperado.'),

-- DOCES (id_subcategoria = 41)
('Brigadeiro Gourmet 30g', 2.50, 300, '2025-11-15', 7, 41, 'https://superprix.vteximg.com.br/arquivos/ids/211267-600-600/brigadeiro-gourmet.png', 'Brigadeiro feito com chocolate nobre e granulado belga.'),
('Beijinho 30g', 2.50, 300, '2025-11-15', 7, 41, 'https://superprix.vteximg.com.br/arquivos/ids/211268-600-600/beijinho-gourmet.png', 'Beijinho tradicional com coco ralado e leite condensado.'),

-- BISCOITOS ARTESANAIS (id_subcategoria = 42)
('Biscoito de Polvilho 100g', 6.90, 100, '2026-01-20', 7, 42, 'https://superprix.vteximg.com.br/arquivos/ids/209453-600-600/biscoito-polvilho.png', 'Biscoito artesanal de polvilho crocante e leve.'),
('Cookie de Chocolate 120g', 8.90, 90, '2026-02-15', 7, 42, 'https://superprix.vteximg.com.br/arquivos/ids/211231-600-600/cookie-chocolate.png', 'Cookie artesanal com gotas de chocolate meio amargo.'),

-- PRODUTOS INTEGRAIS (id_subcategoria = 43)
('Bolo Integral de Cenoura 500g', 14.90, 50, '2025-12-05', 7, 43, 'https://superprix.vteximg.com.br/arquivos/ids/210608-600-600/bolo-integral-cenoura.png', 'Bolo integral de cenoura com cobertura leve de cacau.'),
('Cookies Integrais com Aveia 150g', 9.90, 80, '2026-02-10', 7, 43, 'https://superprix.vteximg.com.br/arquivos/ids/211269-600-600/cookies-aveia.png', 'Cookies integrais com aveia e mel, ricos em fibras.');


-- PET SHOP
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES
-- RAÇÃO (id_subcategoria = 43)
('Ração Golden Cães Adultos 1kg', 25.00, 200, '2026-10-10', 8, 43, 'https://example.com/racao-golden.png', 'Ração completa para cães adultos, sabor frango e arroz.'),
('Ração Whiskas Gatos Adultos 1kg', 22.90, 150, '2026-09-12', 8, 43, 'https://example.com/racao-whiskas.png', 'Ração seca sabor carne para gatos adultos.'),

-- PETISCOS (id_subcategoria = 44)
('Biscoito Pedigree Biscrok 500g', 18.50, 180, '2026-08-30', 8, 44, 'https://example.com/pedigree-biscrok.png', 'Petisco crocante e nutritivo para cães.'),
('Petisco Whiskas Temptations 80g', 9.90, 250, '2026-09-25', 8, 44, 'https://example.com/whiskas-temptations.png', 'Petiscos sabor frango para gatos adultos.'),

-- BRINQUEDOS (id_subcategoria = 45)
('Bola de Borracha Mordedor', 14.90, 100, '2028-01-01', 8, 45, 'https://example.com/bola-borracha.png', 'Brinquedo resistente para cães de médio porte.'),
('Varinha com Pena Gatos', 12.50, 120, '2028-01-01', 8, 45, 'https://example.com/varinha-pena.png', 'Brinquedo interativo com pena para gatos.'),

-- HIGIENE E CUIDADOS (id_subcategoria = 46)
('Shampoo Pet Clean Neutro 500ml', 19.90, 120, '2027-01-05', 8, 46, 'https://example.com/shampoo-petclean.png', 'Shampoo neutro para todos os tipos de pelos.'),
('Lenços Umedecidos Pety 100un', 16.90, 130, '2027-03-10', 8, 46, 'https://example.com/lencos-pety.png', 'Lenços higiênicos para limpeza diária de pets.'),

-- ACESSÓRIOS (id_subcategoria = 47)
('Coleira Ajustável G', 24.90, 80, '2028-01-01', 8, 47, 'https://example.com/coleira-ajustavel.png', 'Coleira de nylon ajustável, resistente e confortável.'),
('Comedouro Inox 500ml', 17.90, 90, '2028-01-01', 8, 47, 'https://example.com/comedouro-inox.png', 'Comedouro em aço inoxidável, fácil de higienizar.'),

-- SAÚDE E MEDICAMENTOS (id_subcategoria = 48)
('Antipulgas NexGard 10-25kg', 85.00, 50, '2027-06-15', 8, 48, 'https://example.com/nexgard.png', 'Tablete mastigável antipulgas e carrapatos para cães.'),
('Vermífugo Drontal Gatos 4 Comprimidos', 39.90, 70, '2027-05-20', 8, 48, 'https://example.com/drontal.png', 'Vermífugo completo para gatos adultos.');

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
