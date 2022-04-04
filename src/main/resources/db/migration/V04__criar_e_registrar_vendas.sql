CREATE TABLE venda (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	data DATE NOT NULL,
	id_cliente BIGINT NOT NULL,
	ativo BOOLEAN NOT NULL,
	FOREIGN KEY (id_cliente) REFERENCES cliente(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO venda(data, id_cliente, ativo) values ('2021-01-02', 1, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-01-25', 1, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-01-12', 2, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-01-12', 3, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-01-30', 3, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-02-02', 4, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-02-02', 5, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-02-02', 7, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-02-09', 7, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-02-12', 8, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-02-15', 9, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-02-17', 9, true);
INSERT INTO venda(data, id_cliente, ativo) values ('2021-02-17', 10, true);