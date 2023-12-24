# dream_project 🚀

## Tecnologias aplicadas

* kotlin 1.8.22 com java 17
* spring framework versão 3.x.x
* JPA
* Mysql
* Lombok
* AWS SNS
* ... mais coisas ( ao infinito e além ) 🚀

## doc da api

- como acessar o swagger UI : http://localhost:8080/swagger-ui/index.html
- como acessar a documentação do openDocs: http://localhost:8080/api-docs

+ GithubPages : https://brunodanielpf.github.io/page-swagger-UI-aluguel-por-temporada/

### MySQLCommands - db

* showdatabases; = mostra as bases de dados que estão no mysql
* use db; = seleciona o banco de dados
* create table; = cria uma tabela / ex: create table clientes(id int primary key, nome varchar (255));
* show tables; = mostra as tabelas existentes
* describe tb_name; = descreve a tabela
* select * from tb_name; = mostra os dados que estão na tabela

### Observabilidade

A aplicação conta com métricas usando 2 recursos que estão no docker-compose, prometheus e grafana. Ambos estão configurados. Para usar siga os passos abaixo:

com o ambiente e aplicação em execução. 
> com o container em execução

poderá gerar requisições na aplicação e visualizar as métricas no prometheus e criar dashboards no grafana usando o datasource do prometheus.

abaixo as URLs para acessar os recursos:

- prometheus : http://localhost:9090/
> ira abrir a plataforma local do prometheus. e usar expressões para consultar as metricas da aplicação voce pode buscar essas metricas em http://host.docker.internal:1234/actuator/prometheus
- grafana: http://localhost:3000/
> ira exigir login e senha, a senha e login é, user: admin e senha: admin, repita depois para confirmar e terá acesso a plataforma do grafana localmente.

## Recursos da AWS com terraform (infra as code)

### requisitos:

+ necessario aws cli e docker

A criação dos recursos da aws que a aplicação precisa estão nos módulos terraform. Usando a imagem terraform através das configurações em `infra/terraform` será criado e configurado o ambiente, criando assim os seguintes componentes:

+ SNS
+ S3