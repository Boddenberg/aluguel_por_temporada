CREATE TABLE endereco (
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
    data_nascimento VARCHAR(10),
    responsavel BOOLEAN,
    endereco_id BIGINT,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

-- comando para ter um dado de teste para fazer obtenção
INSERT INTO endereco (id, logradouro, numero, complemento, bairro, cidade, estado, cep)
VALUES (1, 'logradouro', '300', 'esquina', 'bairro', 'SP', 'SP', '3124124-22');
INSERT INTO tb_cliente (cpf, nome, sobrenome, email, telefone, data_nascimento, responsavel, endereco_id)
VALUES ('666-666-666-66', 'nome- teste', 'sobrenome- teste', 'teste@gmail.com', '55-55555-5555', '10/08/1999', true, 1);
