CREATE TABLE tb_endereco (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255) NOT NULL,
    localidade VARCHAR(255) NOT NULL,
    uf VARCHAR(255) NOT NULL,
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
    politica_desconto_id INT,
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
    anfitriao_cpf VARCHAR(14) NOT NULL,
    hospedagem_id INT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    duracao_reserva INT NOT NULL,
    preco_total DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50)  NOT NULL,
    FOREIGN KEY (cliente_cpf) REFERENCES tb_cliente(cpf),
    FOREIGN KEY (anfitriao_cpf) REFERENCES tb_cliente(cpf),
    FOREIGN KEY (hospedagem_id) REFERENCES tb_hospedagem(id)
);

CREATE TABLE tb_cliente_hospedagem (
    id int PRIMARY KEY AUTO_INCREMENT,
    cliente_cpf VARCHAR(14) NOT NULL,
    FOREIGN KEY (cliente_cpf) REFERENCES tb_cliente(cpf)
);

CREATE TABLE tb_cliente_hospedagem_accommodations (
    guest_accommodations_id INT,
    accommodations_id INT,
    PRIMARY KEY (guest_accommodations_id, accommodations_id),
    FOREIGN KEY (guest_accommodations_id) REFERENCES tb_cliente_hospedagem(id),
    FOREIGN KEY (accommodations_id) REFERENCES tb_hospedagem(id)
);
