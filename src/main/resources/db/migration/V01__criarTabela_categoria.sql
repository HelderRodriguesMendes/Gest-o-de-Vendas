CREATE TABLE categoria (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO categoria (nome, ativo) values ('Tecnologia', true);
INSERT INTO categoria (nome, ativo) values ('Acessórios para veículos', true);
INSERT INTO categoria (nome, ativo) values ('Esporte e Lazer', true);
INSERT INTO categoria (nome, ativo) values ('Casa e Eletrodomésticos', true);
INSERT INTO categoria (nome, ativo) values ('Joias e Relógios', true);