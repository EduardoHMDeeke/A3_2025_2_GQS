-- Seed para o banco MySQL utilizado pelo ToolTracker

CREATE DATABASE IF NOT EXISTS dbtooltracker
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE dbtooltracker;

DROP TABLE IF EXISTS emprestimos;
DROP TABLE IF EXISTS ferramentas;
DROP TABLE IF EXISTS amigos;

CREATE TABLE amigos (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nome       VARCHAR(100) NOT NULL,
    idade      INT          NULL,
    telefone   VARCHAR(20)  NULL,
    email      VARCHAR(120) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE ferramentas (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    nome           VARCHAR(120) NOT NULL,
    marca          VARCHAR(80)  NOT NULL,
    preco          DECIMAL(10,2) NOT NULL,
    estaEmprestada TINYINT(1) NOT NULL DEFAULT 0,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE emprestimos (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    idAmigo        INT NOT NULL,
    idFerramenta   INT NOT NULL,
    dataEmprestimo DATE NULL,
    dataDevolucao  DATE NULL,
    dataDevolvida  DATE NULL,
    estaEmprestada TINYINT(1) NOT NULL DEFAULT 1,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_emprestimos_amigos
        FOREIGN KEY (idAmigo) REFERENCES amigos(id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_emprestimos_ferramentas
        FOREIGN KEY (idFerramenta) REFERENCES ferramentas(id)
        ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

INSERT INTO amigos (nome, idade, telefone, email) VALUES
('Ana Souza',            28, '11988887777', 'ana.souza@example.com'),
('Bruno Fernandes',      35, '11991112222', 'bruno.fernandes@example.com'),
('Carla Medeiros',       31, '21997776666', 'carla.medeiros@example.com'),
('Diego Martins',        42, '31995554444', 'diego.martins@example.com');

INSERT INTO ferramentas (nome, marca, preco, estaEmprestada) VALUES
('Furadeira de Impacto', 'Bosch',         459.90, 0),
('Serra Circular 7 1/4"', 'Makita',       699.00, 1),
('Parafusadeira 12V',    'DeWalt',        399.50, 0),
('Lixadeira Orbital',    'Black+Decker',  289.99, 1),
('Chave de Impacto',     'Stanley',       512.70, 0);

INSERT INTO emprestimos (
    idAmigo, idFerramenta, dataEmprestimo, dataDevolucao, dataDevolvida, estaEmprestada
) VALUES
(2, 2, '2024-10-12', '2024-10-20', NULL, 1),
(3, 4, '2024-10-15', '2024-10-22', NULL, 1),
(1, 3, '2024-09-05', '2024-09-12', '2024-09-11', 0),
(4, 5, '2024-08-18', '2024-08-25', '2024-08-24', 0);

