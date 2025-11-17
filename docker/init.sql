-- Script de inicialização do banco de dados ToolTracker
-- Este script é executado automaticamente quando o container MySQL é criado pela primeira vez

USE dbtooltracker;

-- Tabela de Amigos
CREATE TABLE IF NOT EXISTS amigos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT DEFAULT 0,
    telefone VARCHAR(20),
    email VARCHAR(255),
    INDEX idx_nome (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela de Ferramentas
CREATE TABLE IF NOT EXISTS ferramentas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    marca VARCHAR(255),
    preco VARCHAR(50),
    valor VARCHAR(50),
    estaEmprestada INT DEFAULT 0 COMMENT '0 = disponível, 1 = emprestada',
    INDEX idx_nome (nome),
    INDEX idx_estaEmprestada (estaEmprestada)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela de Empréstimos
CREATE TABLE IF NOT EXISTS emprestimos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idAmigo INT NOT NULL,
    idFerramenta INT NOT NULL,
    dataEmprestimo DATE NOT NULL,
    dataDevolucao DATE NOT NULL,
    dataDevolvida DATE NULL,
    estaEmprestada INT DEFAULT 1 COMMENT '0 = devolvido, 1 = emprestado',
    FOREIGN KEY (idAmigo) REFERENCES amigos(id) ON DELETE CASCADE,
    FOREIGN KEY (idFerramenta) REFERENCES ferramentas(id) ON DELETE CASCADE,
    INDEX idx_idAmigo (idAmigo),
    INDEX idx_idFerramenta (idFerramenta),
    INDEX idx_estaEmprestada (estaEmprestada)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Seed: Inserção de dados iniciais

-- Amigos de exemplo
INSERT INTO amigos (nome, idade, telefone, email) VALUES
('João Silva', 28, '(11) 98765-4321', 'joao.silva@email.com'),
('Maria Santos', 35, '(11) 91234-5678', 'maria.santos@email.com'),
('Pedro Oliveira', 22, '(11) 99876-5432', 'pedro.oliveira@email.com'),
('Ana Costa', 30, '(11) 97654-3210', 'ana.costa@email.com'),
('Carlos Ferreira', 45, '(11) 92345-6789', 'carlos.ferreira@email.com');

-- Ferramentas de exemplo
INSERT INTO ferramentas (nome, marca, preco, valor, estaEmprestada) VALUES
('Furadeira Elétrica', 'Bosch', 'R$ 450,00', 'R$ 450,00', 0),
('Parafusadeira', 'Makita', 'R$ 320,00', 'R$ 320,00', 0),
('Serra Circular', 'DeWalt', 'R$ 580,00', 'R$ 580,00', 0),
('Lixadeira Orbital', 'Black+Decker', 'R$ 280,00', 'R$ 280,00', 0),
('Martelo Demolidor', 'Bosch', 'R$ 1.200,00', 'R$ 1.200,00', 0),
('Trena Laser', 'Leica', 'R$ 850,00', 'R$ 850,00', 0),
('Nível a Laser', 'Bosch', 'R$ 420,00', 'R$ 420,00', 0),
('Esmerilhadeira Angular', 'Makita', 'R$ 380,00', 'R$ 380,00', 0);

-- Empréstimos de exemplo
INSERT INTO emprestimos (idAmigo, idFerramenta, dataEmprestimo, dataDevolucao, estaEmprestada) VALUES
(1, 1, '2024-11-01', '2024-11-15', 1),
(2, 3, '2024-11-05', '2024-11-20', 1),
(3, 5, '2024-10-28', '2024-11-10', 0);

-- Mensagem de confirmação
SELECT 'Banco de dados ToolTracker inicializado com sucesso!' AS Status;
SELECT COUNT(*) AS TotalAmigos FROM amigos;
SELECT COUNT(*) AS TotalFerramentas FROM ferramentas;
SELECT COUNT(*) AS TotalEmprestimos FROM emprestimos;

