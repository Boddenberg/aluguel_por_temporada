# dream_project 🚀

## Tecnologias aplicadas

* kotlin 1.8.22 com java 17
* spring framework versão 3.x.x
    * starter web mvc
* JPA
  * Mysql
* Lombok
* spring-docker-compose
* ... mais coisas ( ao infinito e além ) 🚀

## Regra de negócio da api
...

## DockerCommands

Como acessar a base de dados dentro do container docker
```shell
mysql -u root -p 
```
Acesse a base criada via compose

```shell
use db;
```

### MySQLCommands - db

* showdatabases; = mostra as bases de dados que estão no mysql
* use db; = seleciona o banco de dados
* create table; = cria uma tabela / ex: create table clientes(id int primary key, nome varchar (255));
* show tables; = mostra as tabelas existentes
* describe tb_name; = descreve a tabela
