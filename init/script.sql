CREATE TABLE tb_endereco (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    cep VARCHAR(10) NOT NULL
);

CREATE TABLE tb_cliente (
    cpf VARCHAR(255) NOT NULL PRIMARY KEY,
    nome VARCHAR(255),
    sobrenome VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255),
    data_nascimento VARCHAR(255),
    responsavel BOOLEAN,
    anfitriao BOOLEAN,
    endereco_id BIGINT,
    FOREIGN KEY (endereco_id) REFERENCES tb_endereco(id)
);

CREATE TABLE tb_politica_desconto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo_politica VARCHAR(50),
    desconto DECIMAL(5, 2),
    hospedagem_id INT
);

CREATE TABLE tb_hospedagem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL,
    localizacao VARCHAR(255),
    capacidade INT NOT NULL,
    preco_por_noite DECIMAL(10, 2) NOT NULL,
    endereco_id BIGINT,
    FOREIGN KEY (endereco_id) REFERENCES tb_endereco(id)
);

CREATE TABLE tb_hospedagem__discount_policy (
    accommodation_id INT NOT NULL,
    _discount_policy_id INT NOT NULL,
    FOREIGN KEY (accommodation_id) REFERENCES tb_hospedagem (id),
    FOREIGN KEY (_discount_policy_id) REFERENCES tb_politica_desconto (id)
);

CREATE TABLE tb_reserva (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_cpf VARCHAR(14) NOT NULL,
    hospedagem_id INT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    duracao_reserva INT NOT NULL,
    preco_total DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50)  NOT NULL,
    FOREIGN KEY (cliente_cpf) REFERENCES tb_cliente(cpf),
    FOREIGN KEY (hospedagem_id) REFERENCES tb_hospedagem(id)
);

-- comando para ter um dado de teste para fazer obtenção
INSERT INTO tb_endereco (id, logradouro, numero, complemento, bairro, cidade, estado, cep)
VALUES (1, 'logradouro', '300', 'esquina', 'bairro', 'SP', 'SP', '3124124-22');

INSERT INTO tb_cliente (cpf, nome, sobrenome, email, telefone, data_nascimento, responsavel, anfitriao, endereco_id)
VALUES ('666-666-666-66', 'nome- teste', 'sobrenome- teste', 'teste@gmail.com', '55-55555-5555', '10/08/1999', true, true, 1);

INSERT INTO tb_hospedagem (tipo, localizacao, capacidade, preco_por_noite, endereco_id)
VALUES('Casa', 'Perto da Praia', 6, 250.00, 1);

INSERT INTO tb_reserva (cliente_cpf, hospedagem_id, data_inicio, data_fim, status)
VALUES('666-666-666-66', 1, '2023-05-10', '2023-05-17', 'CONCLUIDA');