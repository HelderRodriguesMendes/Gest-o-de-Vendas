CREATE TABLE item_venda (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	id_produto BIGINT NOT NULL,
	id_venda BIGINT NOT NULL,
	quantidade INTEGER NOT NULL,
	preco_vendido DECIMAL(10,2) NOT NULL,
	ativo BOOLEAN NOT NULL,
	
	FOREIGN KEY (id_produto) REFERENCES produto(id),
	FOREIGN KEY (id_venda) REFERENCES venda(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (1, 1, 1, 870, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (15, 1, 1, 249, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (2, 2, 1, 1623.20, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (3, 3, 1, 1073.36, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (4, 4, 1, 1899, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (5, 5, 1, 3300, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (6, 6, 1, 700, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (7, 7, 1, 800, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (8, 8, 1, 900, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (9, 9, 3, 419.70, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (16, 9, 1, 160.50, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (10, 10, 1, 106.80, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (17, 10, 1, 299.90, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (11, 11, 1, 424.86, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (12, 12, 1, 1164.94, true);
INSERT INTO item_venda(id_produto, id_venda, quantidade, preco_vendido, ativo) values (13, 13, 1, 415.90, true);