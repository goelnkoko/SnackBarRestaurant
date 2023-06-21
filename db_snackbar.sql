create database db_snackbar;

show databases;

use db_snackbar;

create table cliente (
	cod int auto_increment primary key not null,
    nome varchar(50) not null, 
    endereco varchar(100) not null
	);

alter table cliente add column telefone varchar(20) not null;

select * from cliente;

truncate cliente;

create table pedido (
	cod_ped int auto_increment primary key, 
    taxaDeServico double not null, 
    preco_total double not null, 
    pagamento double not null, 
    troco double not null
    );

alter table pedido add column fk_cliente int not null;
alter table pedido add constraint fk_cod_cliente 
	foreign key(fk_cliente) references cliente(cod);

select cod_ped, nome, preco_total, pagamento, troco from pedido, cliente;

create table prato (
	cod_prato int auto_increment primary key not null,
    preco_ven double not null, 
    peso double not null, 
    recheio varchar(25) not null, 
    quantidade int not null,
    preco_tot double not null,
    fk_pedido int not null
    );
 
alter table prato add constraint fk_cod_pedido foreign key (fk_pedido) references pedido(cod_ped);

create table pizza (
	cod_pizza int auto_increment primary key not null, 
    cobertura varchar(20) not null, 
    molho varchar(20) not null, 
    cob_rech boolean not null, 
    fk_prato int not null
    );
    
alter table pizza add constraint fk_cod_prato foreign key (fk_prato) references prato(cod_prato);

select * from pizza;
select * from prato;
select * from pizza, prato where cod_pizza = cod_prato;
