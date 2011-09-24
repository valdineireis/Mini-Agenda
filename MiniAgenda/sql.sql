-- No prompt de comandos informe os seguintes codigos

-- Inicia o MySQL
mysql -u nome_do_usuario -p senha

-- Exibe as bases de dados criadas
show databases;

-- Cria a base de dados com o nome miniagenda
CREATE DATABASE miniagenda;

-- Seleciona uma base de dados para o trabalho
use miniagenda;

-- Cria uma tabela com o nome contato
CREATE TABLE contato (
  id int(11) NOT NULL AUTO_INCREMENT,
  nome varchar(80) NOT NULL,
  telefone varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

-- NO FINAL DE CADA COMANDO INFORME O ; (PONTO E VIRGULA)
