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
INSERT INTO tb_fornecedor (id_Fornecedor, nome_Fornecedor, email_Fornecedor, cpf_Fornecedor, telefone_fornecedor, cnpj) VALUES
(1, 'Fornecedor A', 'fornecedorA@email.com', '22233344455', '15999282837', '12345678000199'),
(2, 'Fornecedor B', 'fornecedorB@email.com', '33344455566', '15981725747', '98765432000188');

-- ======================================
-- CATEGORIAS
-- ======================================
INSERT INTO tb_categoria (nome_Categoria, img_url) VALUES
('Hortifruti', 'https://i.postimg.cc/PrVKWyHs/categoria-Horti-Fruti.png'),
('Bebidas', 'https://i.postimg.cc/Vk5GpTgY/categoria-Bebidas.png'),
('Mercearia', 'https://i.postimg.cc/DZcP8d6H/categoria-Mercearia.png'),
('Limpeza', 'https://i.postimg.cc/43V5TT9H/categoria-Limpeza.png'),
('Açougue', 'https://i.postimg.cc/tRxXQSrv/categoria-Acougue.png'),
('Higiene', 'https://i.postimg.cc/3RQtywTJ/categoria-Higiene.png'),
('Padaria', 'https://i.postimg.cc/brC0RyKH/categoria-Padaria.png'),
('Petshop', 'https://i.postimg.cc/hPv1tmmB/categoria-Pet-Shop.png');

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
('Coca-Cola Lata 350ml', 3.20, 300, '2026-08-20', 2, 7, 'https://prezunic.vtexassets.com/arquivos/ids/210693/66db573a62edc14e790f8550.jpg?v=638612475473130000', 'Coca-Cola Original em lata.'),
('Guaraná Antarctica 2L', 8.50, 180, '2026-07-30', 2, 7, 'https://www.jauserve.com.br/dw/image/v2/BFJL_PRD/on/demandware.static/-/Sites-jauserve-master/default/dw11379f35/7891991001373.jpg?sw=1800', 'Refrigerante Guaraná Antarctica 2 litros.'),

-- SUCOS (id_subcategoria = 8)
('Suco de Laranja Natural One 1L', 9.90, 120, '2026-02-15', 2, 8, 'https://carrefourbrfood.vtexassets.com/arquivos/ids/132095434/suco-de-laranja-integral-refrigerado-natural-one-100--suco-900ml-2.jpg?v=638322173498970000', 'Suco 100% natural, sem conservantes.'),
('Suco de Uva Integral Aurora 1,5L', 14.90, 90, '2026-03-01', 2, 8, 'https://fortatacadista.vteximg.com.br/arquivos/ids/299319-1000-1000/31577-SUCO-CC-AURORA-15L-VD-UVA-TTO.jpg?v=637751712868100000', 'Suco integral de uva Aurora, sem adição de açúcar.'),

-- ÁGUAS (id_subcategoria = 9)
('Água Mineral Crystal Sem Gás 500ml', 2.00, 400, '2028-05-10', 2, 9, 'https://mambodelivery.vtexassets.com/arquivos/ids/184965/agua-mineral-sem-gas-crystal-500ml.jpg?v=637884009070200000', 'Água mineral natural Crystal, sem gás.'),
('Água Mineral com Gás São Lourenço 510ml', 3.50, 250, '2028-05-10', 2, 9, 'https://d3gdr9n5lqb5z7.cloudfront.net/fotos/996404-1.jpg', 'Água gaseificada leve e refrescante.'),

-- CERVEJAS (id_subcategoria = 10)
('Cerveja Heineken Long Neck 330ml', 6.90, 180, '2026-04-15', 2, 10, 'https://coopsp.vtexassets.com/arquivos/ids/226280/78936683.png?v=638042370336500000', 'Cerveja puro malte Heineken long neck.'),
('Cerveja Budweiser Lata 350ml', 5.80, 200, '2026-04-20', 2, 10, 'https://superprix.vteximg.com.br/arquivos/ids/213423-600-600/7891991010481---Cerveja-BUDWEISER-Lata-350ML---1.jpg?v=638194101489800000', 'Cerveja Budweiser tradicional em lata.'),

-- VINHOS (id_subcategoria = 11)
('Vinho Tinto Seco Miolo Reserva 750ml', 49.90, 50, '2030-12-01', 2, 11, 'https://18666.cdn.simplo7.net/static/18666/sku/vinho-tinto-vinho-miolo-reserva-cabernet-sauvignon-tinto-seco-750ml--p-1624559616405.jpg', 'Vinho tinto seco Miolo Reserva Cabernet Sauvignon.'),
('Vinho Branco Suave Salton 750ml', 39.90, 60, '2030-12-01', 2, 11, 'https://obahortifruti.vtexassets.com/arquivos/ids/8446727/Espumante-Nacional-Salton-Demi-Seco-750-Ml.jpg?v=638498190956170000', 'Vinho branco suave Salton, sabor leve e frutado.'),

-- DESTILADOS (id_subcategoria = 12)
('Vodka Smirnoff 998ml', 41.90, 80, '2032-06-01', 2, 12, 'https://tdc1fe.vteximg.com.br/arquivos/ids/159601-1000-1000/VODKA-SMIRNOFF-998ML-TRADICIONAL.jpg?v=637490166901300000', 'Vodka Smirnoff tripla destilação.'),
('Whisky Johnnie Walker Red Label 1L', 129.90, 40, '2035-08-01', 2, 12, 'https://espacoprime.fbitsstatic.net/img/p/johnnie-walker-red-label-blended-scotch-whisky-1l-72713/258460-1.jpg?w=800&h=800&v=no-change&qs=ignore', 'Whisky escocês de sabor marcante.'),

-- ENERGÉTICOS (id_subcategoria = 13)
('Energético Red Bull Lata 250ml', 9.50, 300, '2026-01-01', 2, 13, 'https://bretas.vtexassets.com/arquivos/ids/224061-800-auto?v=638923463816900000&width=800&height=auto&aspect=true', 'Energético Red Bull, te dá asas.'),
('Energético Monster 473ml', 11.90, 250, '2026-01-01', 2, 13, 'https://drogariaspacheco.vteximg.com.br/arquivos/ids/659673-1000-1000/641693---energetico-monster-energy-473ml-spal.jpg?v=637496175101070000', 'Energético Monster Energy tradicional.'),

-- LEITES E DERIVADOS (id_subcategoria = 14)
('Leite Integral Itambé 1L', 5.40, 300, '2025-12-15', 2, 14, 'https://redemix.vteximg.com.br/arquivos/ids/215885-1000-1000/7896051111016.png?v=638394704811300000', 'Leite integral Itambé, fonte de cálcio.'),
('Leite Desnatado Piracanjuba 1L', 5.20, 280, '2025-12-15', 2, 14, 'https://phygital-files.mercafacil.com/catalogo/uploads/produto/leite_uht_desnatado_piracanjuba_caixa_com_tampa_1l_2b0fc2ca-903c-48c2-b599-eed8ff19f119.jpg', 'Leite desnatado Piracanjuba, leve e nutritivo.');

-- MERCEARIA
INSERT INTO tb_produto 
(nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES

-- ARROZ E FEIJÃO (id_subcategoria = 15)
('Arroz Tio João Tipo 1 5kg', 24.90, 200, '2026-08-10', 3, 15, 'https://prezunic.vtexassets.com/arquivos/ids/179922/656789b41ef3739680761222.jpg?v=638368810799430000', 'Arroz branco tipo 1, grãos selecionados.'),
('Feijão Carioca Camil 1kg', 9.80, 180, '2026-05-05', 3, 15, 'https://carrefourbrfood.vtexassets.com/arquivos/ids/195175/871281_1.jpg?v=637272434435130000', 'Feijão carioca de alta qualidade, sabor tradicional.'),

-- MASSAS (id_subcategoria = 16)
('Macarrão Espaguete Renata 500g', 5.90, 160, '2026-06-15', 3, 16, 'https://carrefourbrfood.vtexassets.com/arquivos/ids/12908693/macarrao-espaguete-renata-com-ovos-n%C2%BA-9-500g-1.jpg?v=637477866363900000', 'Macarrão espaguete Renata, cozimento rápido e textura ideal.'),
('Macarrão Parafuso Adria 500g', 5.70, 140, '2026-07-01', 3, 16, 'https://www.adria.com.br/wp-content/uploads/2020/11/7896205788156.png', 'Macarrão tipo parafuso Adria, ideal para saladas e molhos.'),

-- FARINHAS E CEREAIS (id_subcategoria = 17)
('Farinha de Trigo Dona Benta 1kg', 6.40, 120, '2026-09-01', 3, 17, 'https://redemix.vteximg.com.br/arquivos/ids/208525-1000-1000/7896005202074.jpg?v=638350600984270000', 'Farinha de trigo tipo 1 para bolos e pães.'),
('Aveia em Flocos Finos Quaker 170g', 4.90, 100, '2026-04-01', 3, 17, 'https://static.extramercado.com.br/img/uploads/1/719/33229719.png?im=Resize,width=200', 'Aveia em flocos finos Quaker, ideal para vitaminas e mingaus.'),

-- ENLATADOS E CONSERVAS (id_subcategoria = 18)
('Milho Verde Quero 200g', 3.50, 150, '2027-01-15', 3, 18, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_nnxtJ3R_jk9FKki7YqhTUeunYmmcCearxg&s', 'Milho verde em conserva, pronto para consumo.'),
('Ervilha Predilecta 200g', 3.20, 130, '2027-01-10', 3, 18, 'https://megag.com.br/v21/wp-content/uploads/2021/07/arq_0Ervilha-Lata-Predilecta-200g.jpg', 'Ervilhas macias e saborosas em conserva.'),

-- MOLHOS E TEMPEROS (id_subcategoria = 19)
('Molho de Tomate Pomarola Tradicional 340g', 4.60, 180, '2027-02-01', 3, 19, 'https://redemix.vteximg.com.br/arquivos/ids/212666-1000-1000/7896036036051.jpg?v=638350618233070000', 'Molho de tomate Pomarola com sabor caseiro.'),
('Tempero Sazón Alho e Sal 60g', 7.30, 200, '2027-05-01', 3, 19, 'https://redemix.vteximg.com.br/arquivos/ids/207825-1000-1000/7891132008698.jpg?v=638350598607500000', 'Tempero prático para realçar o sabor dos pratos.'),

-- ÓLEOS E GORDURAS (id_subcategoria = 20)
('Óleo de Soja Liza 900ml', 8.90, 180, '2026-11-01', 3, 20, 'https://www.jauserve.com.br/dw/image/v2/BFJL_PRD/on/demandware.static/-/Sites-jauserve-master/default/dw817c3aea/7896036091234.png?sw=1800', 'Óleo de soja refinado, ideal para frituras e refogados.'),
('Azeite Gallo Extra Virgem 500ml', 29.90, 100, '2028-03-01', 3, 20, 'https://a-static.mlcdn.com.br/800x600/azeite-de-oliva-extra-virgem-gallo-reserva-500ml/magazineluiza/226474100/8b834df32d87044c61a7416aeaac2ff1.jpg', 'Azeite extra virgem Gallo, sabor intenso e frutado.'),

-- AÇÚCAR E CAFÉ (id_subcategoria = 21)
('Açúcar Refinado União 1kg', 4.20, 250, '2026-08-01', 3, 21, 'https://redemix.vteximg.com.br/arquivos/ids/211573-1000-1000/7891910000197.jpg?v=638350614631300000', 'Açúcar refinado União, ideal para receitas e bebidas.'),
('Café Pilão Tradicional 500g', 17.90, 150, '2026-12-01', 3, 21, 'https://www.jauserve.com.br/dw/image/v2/BFJL_PRD/on/demandware.static/-/Sites-jauserve-master/default/dw6cd8cda6/7896089011357-(2).jpg?sw=1800', 'Café torrado e moído Pilão, sabor encorpado e aroma marcante.'),

-- BISCOITOS E SNACKS (id_subcategoria = 22)
('Biscoito Trakinas Chocolate 126g', 3.90, 200, '2026-03-01', 3, 22, 'https://www.caboclodistribuidor.com.br/imagens/produtos/605x605/1453_Biscoito_Trakinas_Recheado_Chocolate_126G_1.jpg', 'Biscoito recheado Trakinas sabor chocolate.'),
('Batata Ruffles Original 96g', 9.50, 180, '2026-05-01', 3, 22, 'https://images.tcdn.com.br/img/img_prod/1176430/batata_original_ruffles_68g_18805_1_4ad1f012df60c1567b1f9f333f1783f7.png', 'Batata chips Ruffles sabor original, crocante e saborosa.');
	
-- LIMPEZA
INSERT INTO tb_produto 
(nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES

-- LIMPEZA DOMÉSTICA (id_subcategoria = 23)
('Desinfetante Pinho Sol 500ml', 7.90, 150, '2027-06-01', 4, 23, 'https://coopsp.vtexassets.com/arquivos/ids/214635/7891024194102.jpg?v=637919522836430000', 'Desinfetante concentrado com aroma de pinho.'),
('Multiuso Veja Original 500ml', 8.20, 200, '2027-06-01', 4, 23, 'https://redemix.vteximg.com.br/arquivos/ids/211084-1000-1000/7891035800214.jpg?v=638350611663400000', 'Limpador multiuso Veja, ideal para superfícies diversas.'),

-- LAVANDERIA (id_subcategoria = 24)
('Sabão em Pó Omo Lavagem Perfeita 1,6kg', 24.90, 120, '2027-03-01', 4, 24, 'https://carrefourbrfood.vtexassets.com/arquivos/ids/199642746/5667046_1.jpg?v=638919979375100000', 'Sabão em pó Omo para roupas mais limpas e perfumadas.'),
('Amaciante Downy Brisa de Verão 1L', 12.50, 140, '2027-04-01', 4, 24, 'https://redemix.vteximg.com.br/arquivos/ids/207126-1000-1000/7506339332643.jpg?v=638350592684400000', 'Amaciante Downy com perfume duradouro e maciez.'),

-- HIGIENE DO LAR (id_subcategoria = 25)
('Esponja de Limpeza Scotch-Brite Leve 4 Pague 3', 8.90, 250, '2028-01-01', 4, 25, 'https://mambodelivery.vtexassets.com/arquivos/ids/166890/esponja-limpeza-extrema-scoth-brite-leve-4-pague-3.jpg?v=637883229433970000', 'Esponjas resistentes, ideais para uso diário na cozinha.'),
('Pano de Chão Bettanin 60x70cm', 6.50, 180, '2028-01-01', 4, 25, 'https://www.bettanin.com.br/wp-content/uploads/2023/01/4971.jpg', 'Pano de chão de algodão com alta absorção.'),

-- INSETICIDAS (id_subcategoria = 26)
('Inseticida Raid Multi Insetos 420ml', 15.90, 160, '2028-07-01', 4, 26, 'https://carrefourbrfood.vtexassets.com/arquivos/ids/32435175/inseticida-multi-insetos-raid-spray-leve-mais-pague-menos-420-ml-1.jpg?v=637781261120000000', 'Inseticida aerossol para eliminar mosquitos e baratas.'),
('Inseticida SBP Elétrico Refil 45 Noites', 18.50, 120, '2028-07-01', 4, 26, 'https://carrefourbrfood.vtexassets.com/arquivos/ids/141255750/repelente-eletrico-liquido-sbp-45-noites-cheiro-suave-novo-aparelho---refil-1.jpg?v=638482839023700000', 'Refil SBP elétrico com ação prolongada contra mosquitos.'),

-- DESCARTÁVEIS (id_subcategoria = 27)
('Sacos de Lixo 50L C/10 Unidades', 10.90, 200, '2029-01-01', 4, 27, 'https://www.jauserve.com.br/dw/image/v2/BFJL_PRD/on/demandware.static/-/Sites-jauserve-master/default/dw70e53305/7898016970508.png?sw=1800', 'Sacos de lixo resistentes, ideais para uso doméstico.'),
('Luvas de Limpeza Scotch-Brite Tamanho M', 8.90, 150, '2029-01-01', 4, 27, 'https://cosama.vteximg.com.br/arquivos/ids/160311-1000-1000/luva_de_latex_natural_amarela_m_scotch_brite_3m.jpg?v=636626010656930000', 'Luvas duráveis e confortáveis, ideais para tarefas domésticas.');

-- AÇOUGUE
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES
-- CARNES BOVINAS (id_subcategoria = 28)
('Picanha Bovina 1kg', 69.90, 40, '2025-12-05', 5, 28, 'https://redemix.vteximg.com.br/arquivos/ids/221020-1000-1000/picanha-fresca-644da1b95ddc6.png?v=638683934671200000', 'Corte nobre de picanha bovina, ideal para churrascos.'),
('Carne Moída de Patinho 500g', 17.90, 70, '2025-11-30', 5, 28, 'https://superprix.vteximg.com.br/arquivos/ids/223360/93152.jpg?v=638616651705300000', 'Carne bovina moída fresca, ideal para molhos e recheios.'),

-- CARNES SUÍNAS (id_subcategoria = 29)
('Bisteca Suína 1kg', 19.90, 60, '2025-11-18', 5, 29, 'https://oceanicaalimentos.com.br/wp-content/uploads/2019/06/1-2.jpg', 'Bisteca suína fresca, ótima para grelhar ou fritar.'),
('Costelinha Suína 1kg', 24.90, 50, '2025-11-20', 5, 29, 'https://fortatacadista.vteximg.com.br/arquivos/ids/161427-1000-1000/COSTELINHA-SUINA-FRIMESA-KG-CONG---684651.jpg?v=637437446247230000', 'Costelinha suína selecionada, ideal para forno ou churrasco.'),

-- AVES (id_subcategoria = 30)
('Filé de Peito de Frango Congelado 1kg', 14.90, 100, '2025-12-10', 5, 30, 'https://mambodelivery.vtexassets.com/arquivos/ids/207134/178925-File-de-Peito-de-Frango-Congelado-Sadia-1kg.jpg?v=638460990526200000', 'Filé de peito de frango congelado, pronto para preparo.'),
('Coxa e Sobrecoxa de Frango 1kg', 12.50, 80, '2025-11-25', 5, 30, 'https://coopsp.vtexassets.com/arquivos/ids/217839/7893000090064.jpg?v=637919541396930000', 'Corte de frango com pele e osso, ideal para assar ou fritar.'),

-- PEIXES (id_subcategoria = 31)
('Filé de Tilápia Congelado 500g', 27.90, 60, '2025-12-20', 5, 31, 'https://phygital-files.mercafacil.com/catalogo/uploads/produto/file_tilapia_costa_sul_500g_s_p_cong_fc01c33e-c821-47e2-8158-fd14636cbac5.jpg', 'Filé de tilápia sem espinhas, leve e saboroso.'),
('Camarão Cinza Médio Congelado 400g', 39.90, 50, '2025-12-30', 5, 31, 'https://fribel.agilecdn.com.br/8232_1.jpg', 'Camarão médio congelado, ideal para moquecas e risotos.'),

-- EMBUTIDOS (id_subcategoria = 32)
('Linguiça Toscana 1kg', 22.50, 90, '2026-01-10', 5, 32, 'https://mercantilnovaera.vtexassets.com/arquivos/ids/217280/Linguica-Toscana-Apimentada-PERDIGAO-Na-Brasa-Pacote-1kg.jpg?v=638536407692470000', 'Linguiça toscana tradicional, ideal para grelhar.'),
('Bacon Fatiado 250g', 14.50, 75, '2025-12-01', 5, 32, 'https://cdn.formulaexpress.com.br/img/p/2/4/8/0/2480-thickbox_default.jpg', 'Bacon fatiado tradicional, ideal para fritar');


-- HIGIENE
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES
-- CABELOS (id_subcategoria = 33)
('Shampoo Seda Hidratação 325ml', 9.90, 120, '2027-05-15', 6, 33, 'https://images.tcdn.com.br/img/img_prod/1148372/shampoo_seda_oleo_hidratacao_com_325ml_1573_1_467aa22d9edf01f80285378821f0aa57.png', 'Shampoo com fórmula hidratante, ideal para cabelos normais e secos.'),
('Condicionador Dove Nutrição 200ml', 12.50, 100, '2027-06-10', 6, 33, 'https://drogariasp.vteximg.com.br/arquivos/ids/545366-1000-1000/325392---condicionador-dove-oleo-nutricao-200ml.jpg?v=637806430006370000', 'Condicionador Dove para cabelos nutridos e macios.'),

-- CORPO E BANHO (id_subcategoria = 34)
('Sabonete Dove Original 90g', 3.50, 300, '2027-02-20', 6, 34, 'https://images.tcdn.com.br/img/img_prod/1309802/sabonete_em_barra_dove_original_90g_4237_1_7194f0e3245cc566b2d35b7f386ac285.jpg', 'Sabonete hidratante com ¼ de creme, ideal para pele macia.'),
('Sabonete Líquido Nivea Erva Doce 250ml', 14.90, 80, '2027-03-15', 6, 34, 'https://drogariasp.vteximg.com.br/arquivos/ids/1238653-1000-1000/166367---sabonete-liquido-nivea-erva-doce-250ml-1.jpg?v=638852663400830000', 'Sabonete líquido corporal com fragrância suave e refrescante.'),

-- HIGIENE ORAL (id_subcategoria = 35)
('Creme Dental Colgate Total 90g', 8.90, 200, '2027-04-30', 6, 35, 'https://carrefourbrfood.vtexassets.com/arquivos/ids/82118975/creme-dental-colgate-total-12-advance-90g-3.jpg?v=638012807341970000', 'Creme dental antibacteriano que protege dentes e gengivas.'),
('Enxaguante Bucal Listerine Cool Mint 500ml', 22.90, 100, '2027-06-20', 6, 35, 'https://drogariasp.vteximg.com.br/arquivos/ids/1267051-1000-1000/22667---Antisseptico-Bucal-Listerine-Cool-Mint-500ml-1.jpg?v=638894016730400000', 'Enxaguante bucal com ação antibacteriana e hálito refrescante.'),

-- CUIDADOS PESSOAIS (id_subcategoria = 36)
('Desodorante Rexona Men Aerosol 150ml', 15.90, 150, '2027-07-10', 6, 36, 'https://destro.fbitsstatic.net/img/p/desodorante-rexona-men-clinical-clean-aerossol-150ml-75969/262523-1.jpg?w=500&h=500&v=202501231555&qs=ignore', 'Desodorante masculino com 48h de proteção e fragrância marcante.'),
('Hidratante Corporal Nivea Milk 200ml', 19.90, 90, '2027-08-05', 6, 36, 'https://drogariavenancio.vtexassets.com/arquivos/ids/1227798/92737_z.jpg?v=638723811818900000', 'Hidratante corporal com óleo de amêndoas, ideal para pele seca.'),

-- BARBEAR E DEPILAÇÃO (id_subcategoria = 37)
('Aparelho de Barbear Gillette Mach3', 39.90, 70, '2028-01-10', 6, 37, 'https://drogariaspacheco.vteximg.com.br/arquivos/ids/1082258/808946---Aparelho-de-Barbear-Gillette-Mach3-Carbono-Recarregavel-1-Unidade-1.jpg?v=638189062979930000', 'Aparelho de barbear com três lâminas e cabo ergonômico.'),
('Creme Depilatório Veet Corporal 100g', 24.90, 60, '2027-09-15', 6, 37, 'https://images-na.ssl-images-amazon.com/images/I/51htloQj3QL._AC_UL675_SR528,675_.jpg', 'Creme depilatório para peles sensíveis, fácil e rápido de usar.');

-- PADARIA
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES
-- PÃES (id_subcategoria = 38)
('Pão Francês 50g', 0.80, 500, '2025-10-30', 7, 38, 'https://phygital-files.mercafacil.com/princesa/uploads/produto/p_o_franc_s_kg_a1fe8694-113a-40c0-b31c-ec145ae5614f.jpg', 'Pão francês crocante por fora e macio por dentro, assado diariamente.'),
('Pão de Forma Integral 500g', 8.90, 120, '2025-12-10', 7, 38, 'https://carrefourbrfood.vtexassets.com/arquivos/ids/190289/9734384_1.jpg?v=637272422314700000', 'Pão de forma integral, rico em fibras e ideal para o café da manhã.'),

-- BOLOS E TORTAS (id_subcategoria = 39)
('Bolo de Chocolate 500g', 16.90, 40, '2025-11-05', 7, 39, 'https://static.cestasmichelli.com.br/images/product/rs-6484-22185-0.jpg?ims=750x750', 'Bolo de chocolate caseiro com cobertura cremosa.'),
('Torta de Chocolate 600g', 22.90, 30, '2025-11-08', 7, 39, 'https://doceamor.com/wp-content/uploads/2018/07/torta-chocolate-1.jpg', 'Torta artesanal de chocolate com base crocante e merengue suave.'),

-- SALGADOS (id_subcategoria = 40)
('Coxinha de Frango 100g', 4.50, 200, '2025-11-01', 7, 40, 'https://panattos.com.br/uploads/produtos/2017/03/coxinha-de-frango-com-requeijao-mini-congelada.jpg', 'Coxinha de frango tradicional, massa leve e recheio cremoso.'),
('Empada de Palmito 90g', 5.20, 180, '2025-11-02', 7, 40, 'https://phygital-files.mercafacil.com/tropical-supermercados/uploads/produto/empada_90g_un_palmito_tropical_506cb2f7-cf77-44ef-8639-203e49187ef8.jpg', 'Empada artesanal recheada com palmito temperado.'),

-- DOCES (id_subcategoria = 41)
('Brigadeiro Gourmet 30g', 2.50, 300, '2025-11-15', 7, 41, 'https://media.istockphoto.com/id/1173585579/pt/foto/brigadier.jpg?s=612x612&w=0&k=20&c=huBBKQPsrmdntZbdmUOKvP_C_VY2RJsfFivRSTJ9kcA=', 'Brigadeiro feito com chocolate nobre e granulado belga.'),
('Beijinho 30g', 2.50, 300, '2025-11-15', 7, 41, 'https://centralmaxsupermercados.com.br/imagens_site/43312.jpg', 'Beijinho tradicional com coco ralado e leite condensado.'),

-- BISCOITOS ARTESANAIS (id_subcategoria = 42)
('Biscoito de Polvilho 100g', 6.90, 100, '2026-01-20', 7, 42, 'https://mambodelivery.vtexassets.com/arquivos/ids/158722/biscoito-de-polvilho-cassini-100g.jpg?v=637883122058900000', 'Biscoito artesanal de polvilho crocante e leve.'),
('Cookie de Chocolate 120g', 8.90, 90, '2026-02-15', 7, 42, 'https://superangeloni.vtexassets.com/arquivos/ids/254364/image-to-upload-0.jpg?v=638594125976230000', 'Cookie artesanal com gotas de chocolate meio amargo.'),

-- PRODUTOS INTEGRAIS (id_subcategoria = 43)
('Bolo Integral de Cenoura 500g', 14.90, 50, '2025-12-05', 7, 43, 'https://www.naturaldaterra.com.br/_next/image?url=https%3A%2F%2Fnaturalterra.vtexassets.com%2Farquivos%2Fids%2F161041%2FBolo-de-Cenoura-com-Creme-de-Avela.jpg%3Fv%3D638671093512630000%26format%3Dwebp&w=1440&q=75', 'Bolo integral de cenoura com cobertura leve de cacau.'),
('Cookies Integrais com Aveia 150g', 9.90, 80, '2026-02-10', 7, 43, 'https://amoradoceria.com.br/wp-content/uploads/2022/01/cookie-de-aveia-com-banana.jpg', 'Cookies integrais com aveia e mel, ricos em fibras.');


-- PET SHOP
INSERT INTO tb_produto (nome_produto, preco_produto, quantidade, data_validade, id_categoria, id_subcategoria, img_url, descricao_produto) VALUES
-- RAÇÃO (id_subcategoria = 43)
('Ração Golden Cães Adultos 1kg', 25.00, 200, '2026-10-10', 8, 44, 'https://agropecuariaimarui.com.br/wp-content/uploads/2019/06/2480.jpg', 'Ração completa para cães adultos, sabor frango e arroz.'),
('Ração Whiskas Gatos Adultos 1kg', 22.90, 150, '2026-09-12', 8, 44, 'https://images.tcdn.com.br/img/img_prod/1087789/racao_whiskas_gatos_adultos_sabor_carne_10_1kg_1854_2_5136de4f3b277b57dffcc4fda1749c1f.jpg', 'Ração seca sabor carne para gatos adultos.'),

-- PETISCOS (id_subcategoria = 44)
('Biscoito Pedigree Biscrok 500g', 18.50, 180, '2026-08-30', 8, 45, 'https://www.casadoprodutor.com.br/media/catalog/product/b/i/biscrok_multi_500g.jpg', 'Petisco crocante e nutritivo para cães.'),
('Petisco Whiskas Temptations 80g', 9.90, 250, '2026-09-25', 8, 45, 'https://cobasi.vteximg.com.br/arquivos/ids/1042496/Whiskas-petisco-anti-bola-de-pelo-80g.jpg?v=638753893780570000', 'Petiscos sabor frango para gatos adultos.'),

-- BRINQUEDOS (id_subcategoria = 45)
('Bola de Borracha Mordedor', 14.90, 100, '2028-01-01', 8, 46, 'https://lojasmel1.vtexassets.com/arquivos/ids/230254/Brinquedo_Pet_Bola_Cravo_Borracha_Colorida_7cm_LM2967PET_-_honeyhome_2_243.jpg?v=638640144799800000', 'Brinquedo resistente para cães de médio porte.'),
('Varinha com Pena Gatos', 12.50, 120, '2028-01-01', 8, 46, 'https://images.tcdn.com.br/img/img_prod/771572/brinquedo_para_gato_varinha_guizo_pena_micanga_34507_2_20230502121054.jpg', 'Brinquedo interativo com pena para gatos.'),

-- HIGIENE E CUIDADOS (id_subcategoria = 46)
('Shampoo Pet Clean 700ml', 19.90, 120, '2027-01-05', 8, 47, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSk2smcR-r0sPXmmrUc3U3ZF_XPw6rYN1iniQ&s', 'Shampoo neutro para todos os tipos de pelos.'),
('Lenços Umedecidos MyHug', 16.90, 130, '2027-03-10', 8, 47, 'https://cobasi.vteximg.com.br/arquivos/ids/1069381/Lenco-Umedecido-Caes-e-Gatos-MyHug-100-unidades.png.png?v=638781849453070000', 'Lenços higiênicos para limpeza diária de pets.'),

-- ACESSÓRIOS (id_subcategoria = 47)
('Coleira Ajustável G', 24.90, 80, '2028-01-01', 8, 48, 'https://m.media-amazon.com/images/I/41Ttmxzzx0L._UF1000,1000_QL80_.jpg', 'Coleira de nylon ajustável, resistente e confortável.'),
('Comedouro Inox 500ml', 17.90, 90, '2028-01-01', 8, 48, 'https://tudodebicho.vtexassets.com/arquivos/ids/172735/comedouro-caes-american-pets-inox-natutral-auteo-relevo.jpg?v=638410396605100000', 'Comedouro em aço inoxidável, fácil de higienizar.'),

-- SAÚDE E MEDICAMENTOS (id_subcategoria = 48)
('Antipulgas NexGard 10-25kg', 85.00, 50, '2027-06-15', 8, 49, 'https://images.tcdn.com.br/img/img_prod/1263384/antipulgas_e_carrapatos_nexgard_cao_10_a_25kg_com_1_comprimido_1143_1_132c0f3065a689fe82a461685055d1c8.jpg', 'Tablete mastigável antipulgas e carrapatos para cães.'),
('Vermífugo Drontal Gatos 4 Comprimidos', 39.90, 70, '2027-05-20', 8, 49, 'https://images.petz.com.br/fotos/1661537404351.jpg', 'Vermífugo completo para gatos adultos.');

-- ======================================
-- PEDIDOS DE CLIENTE
-- ======================================


-- ======================================
-- PEDIDOS DE FORNECEDOR
-- ======================================

