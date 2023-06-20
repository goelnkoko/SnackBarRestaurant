create database db_snackbar;

show databases;

use db_snackbar;

create table cliente (
	cod int auto_increment primary key not null,
    nome varchar(50) not null, 
    endereco varchar(100) not null
	);

create table telefone (
	cod_tel int auto_increment primary key not null, 
    telefone varchar(20) not null, 
    fk_cod_cliente int not null
    );

alter table telefone add constraint fk_cod_cliente foreign key (fk_cod_cliente) references cliente(cod);

select cod, nome, endereco, cod_tel, telefone from cliente, telefone where telefone.fk_cod_cliente = cliente.cod;