CREATE TABLE estado (
	codigo BIGINT PRIMARY KEY,
	nome VARCHAR(50) NOT NULL
);

INSERT INTO estado (codigo, nome) VALUES(1, 'Acre');
INSERT INTO estado (codigo, nome) VALUES(2, 'Alagoas');
INSERT INTO estado (codigo, nome) VALUES(3, 'Amazonas');
INSERT INTO estado (codigo, nome) VALUES(4, 'Amapá');
INSERT INTO estado (codigo, nome) VALUES(5, 'Bahia');
INSERT INTO estado (codigo, nome) VALUES(6, 'Ceará');
INSERT INTO estado (codigo, nome) VALUES(7, 'Distrito Federal');
INSERT INTO estado (codigo, nome) VALUES(8, 'Espírito Santo');
INSERT INTO estado (codigo, nome) VALUES(9, 'Goiás');
INSERT INTO estado (codigo, nome) VALUES(10, 'Maranhão');
INSERT INTO estado (codigo, nome) VALUES(11, 'Minas Gerais');
INSERT INTO estado (codigo, nome) VALUES(12, 'Mato Grosso do Sul');
INSERT INTO estado (codigo, nome) VALUES(13, 'Mato Grosso');
INSERT INTO estado (codigo, nome) VALUES(14, 'Pará');
INSERT INTO estado (codigo, nome) VALUES(15, 'Paraíba');
INSERT INTO estado (codigo, nome) VALUES(16, 'Pernambuco');
INSERT INTO estado (codigo, nome) VALUES(17, 'Piauí');
INSERT INTO estado (codigo, nome) VALUES(18, 'Paraná');
INSERT INTO estado (codigo, nome) VALUES(19, 'Rio de Janeiro');
INSERT INTO estado (codigo, nome) VALUES(20, 'Rio Grande do Norte');
INSERT INTO estado (codigo, nome) VALUES(21, 'Rondônia');
INSERT INTO estado (codigo, nome) VALUES(22, 'Roraima');
INSERT INTO estado (codigo, nome) VALUES(23, 'Rio Grande do Sul');
INSERT INTO estado (codigo, nome) VALUES(24, 'Santa Catarina');
INSERT INTO estado (codigo, nome) VALUES(25, 'Sergipe');
INSERT INTO estado (codigo, nome) VALUES(26, 'São Paulo');
INSERT INTO estado (codigo, nome) VALUES(27, 'Tocantins');

CREATE TABLE cidade (
	codigo BIGINT PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
  	codigo_estado BIGINT NOT NULL,
  	FOREIGN KEY (codigo_estado) REFERENCES estado(codigo)
);

INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (1, 'Palmitos', 24);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (2, 'Chapecó', 24);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (3, 'São Miguel do Oeste', 24);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (4, 'Caibi', 24);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (5, 'Itapema', 24);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (6, 'São Carlos', 24);
INSERT INTO cidade (codigo, nome, codigo_estado) VALUES (7, 'Campo Bom', 23);

CREATE TABLE pessoa (
	codigo BIGINT PRIMARY KEY Generated always as identity,
	nome_completo VARCHAR(100) NOT NULL,
	sexo VARCHAR(1),
	data_nascimento DATE,
	idade SMALLINT,
	codigo_cidade BIGINT,
	FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo)
);

INSERT INTO pessoa (nome_completo, sexo, data_nascimento, idade, codigo_cidade) values ('Felipe Carollo', 'M', '1991-08-04', 29, 1);
INSERT INTO pessoa (nome_completo, sexo, data_nascimento, idade, codigo_cidade) values ('Lucas Carollo', 'M', '1997-07-11', 23, 1);