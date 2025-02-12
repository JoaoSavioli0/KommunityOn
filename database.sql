DROP DATABASE KOMMUNITYON;
CREATE DATABASE KOMMUNITYON;
USE KOMMUNITYON;

SELECT * FROM ENDERECO;
SELECT * FROM SOLICITACAO ORDER BY DATA_ABERTURA;
SELECT * FROM USUARIO ;
SELECT * FROM USUARIO_LIKE_SOLICITACAO;

CREATE TABLE USUARIO(
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
NOME VARCHAR(80) NOT NULL,
CPF VARCHAR(15) NOT NULL UNIQUE,
EMAIL VARCHAR(70) NOT NULL UNIQUE,
SENHA VARCHAR(30) NOT NULL,
TELEFONE VARCHAR(22) NOT NULL,
DATA_INTERACAO_1 TIMESTAMP,
DATA_INTERACAO_2 TIMESTAMP,
TIPO VARCHAR(3) DEFAULT 'USU'
);

INSERT INTO USUARIO(NOME, CPF, EMAIL, SENHA, TELEFONE, TIPO)
VALUES("JOÃO PEDRO", "40172440840", "JOAOPEDROOLISAVIOLI@GMAIL.COM", "123", "11978521152", "USU");

INSERT INTO USUARIO(NOME, CPF, EMAIL, SENHA, TELEFONE)
VALUES("Daniel Cravo", "40172440841", "danielcravo@gmail.com", "12345", "11978521152");

CREATE TABLE ENDERECO(
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_USUARIO BIGINT,
UF VARCHAR(2) NOT NULL,
BAIRRO VARCHAR(60) NOT NULL,
CIDADE VARCHAR(30) NOT NULL,

FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID) ON DELETE CASCADE
);

CREATE TABLE SOLICITACAO(
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
ID_USUARIO BIGINT,
TITULO VARCHAR(60) NOT NULL,
BAIRRO VARCHAR(60) NOT NULL,
DESCRICAO VARCHAR(450) NOT NULL,
NUM_LIKES INT DEFAULT 0,
NUM_COMENTARIOS INT DEFAULT 0,
DATA_ABERTURA TIMESTAMP DEFAULT NOW(),
DATA_CONCLUSAO DATETIME,

FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID) ON DELETE CASCADE
);

DELETE FROM SOLICITACAO WHERE ID = 1;

INSERT INTO SOLICITACAO(ID_USUARIO, TITULO, BAIRRO, DESCRICAO, NUM_LIKES, DATA_ABERTURA) 
VALUES(1, "SEMÁFORO QUEBRADO", "JD. AEROPORTO", "O SEMÁFORO TAL TA QUEBRADO HÁ 3 SEMANAS CAUSANDO TRANSITO NO LOCAL",
5, NOW());

INSERT INTO SOLICITACAO(ID_USUARIO, TITULO, BAIRRO, DESCRICAO, NUM_LIKES, DATA_ABERTURA) 
VALUES(1, "LOMBADA", "JD. AEROPORTO", "PRECISA DE LOMBADA NA RUA TAL",
3, NOW());

INSERT INTO SOLICITACAO(ID_USUARIO, TITULO, BAIRRO, DESCRICAO, NUM_LIKES, DATA_ABERTURA) 
VALUES(1, "BLABLA", "BLABLA", "BLABLABLABLA",
13, NOW());

CREATE TABLE USUARIO_LIKE_SOLICITACAO(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
ID_USUARIO BIGINT,
ID_SOLICITACAO BIGINT,

FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID),
FOREIGN KEY (ID_SOLICITACAO) REFERENCES SOLICITACAO(ID)
);

SELECT * FROM COMENTARIO;

CREATE TABLE COMENTARIO(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
ID_USUARIO BIGINT,
ID_SOLICITACAO BIGINT,
TEXTO VARCHAR(255),

FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (ID) ON DELETE CASCADE,
FOREIGN KEY (ID_SOLICITACAO) REFERENCES SOLICITACAO (ID) ON DELETE CASCADE
);