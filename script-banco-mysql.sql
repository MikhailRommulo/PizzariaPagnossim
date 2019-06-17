CREATE DATABASE db_pizzaria CHARSET = UTF8 COLLATE = utf8_general_ci;

CREATE TABLE cliente (
  cpf bigint NOT NULL,
  nome varchar(45) NOT NULL,
  telefone decimal(9,0) NOT NULL,
  telefone2 decimal(9,0),
  logradouro varchar(100),
  bairro varchar(50),
  cidade varchar(80),
  estado varchar(50),
  email varchar(100),
  PRIMARY KEY (cpf)
);

CREATE TABLE funcionario (
  cpf bigint NOT NULL,
  nome varchar(45) NOT NULL,
  telefone decimal(9,0) NOT NULL,
  nivel int,
  funcao varchar(10),
  PRIMARY KEY (cpf)
);

CREATE TABLE usuario(
	codUsu int not null auto_increment,
	cpfCli bigint,
	cpfFun bigint,
	tipo varchar(20),
	senha varchar(10) not null,
	primary key(codUsu),
	foreign key(cpfCli) references cliente(cpf),
	foreign key(cpfFun) references funcionario(cpf)
);

CREATE TABLE produto (
  codProd int not null auto_increment,
  tipo varchar(20),
  descricao varchar(150),
  preco decimal(5,2),
  quantidade int,
  PRIMARY KEY (codProd)
);

CREATE TABLE pedido (
  codPed int not null auto_increment,
  status varchar(20) NOT NULL,
  precoPedido decimal(6,2),
  PRIMARY KEY (codPed)
);

CREATE TABLE itemPedido (
  codProd int not null auto_increment,
  codPed int not null,
  foreign key(codProd) references produto(codProd),
  foreign key(codPed) references pedido(codPed)
);

CREATE TABLE pedidoFuncionario(
	codPed int NOT NULL,
	codFun int NOT NULL,
	foreign key(codPed) references pedido(codPed),
	foreign key(codFun) references funcionario(cpf)
);
