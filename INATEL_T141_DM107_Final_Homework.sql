create database INATEL_T141_DM107_Final_Homework;

use INATEL_T141_DM107_Final_Homework;

CREATE TABLE entregas (  
	id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
	numeroDoPedido VARCHAR(300),
	idDoCliente int,
	nomeDoRecebedor VARCHAR(300),
	cpfDoRecebedor VARCHAR(50),
	dataHoraDaEntrega TIMESTAMP
);

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("P1", 1, "Recebedor1", "123.456.789-1", "2017-11-01 01:10:01"); 

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("P2", 1, "Recebedor2", "123.456.789-2", "2017-11-02 01:20:02"); 

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("P3", 1, "Recebedor3", "123.456.789-3", "2017-11-03 04:30:03"); 

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("P4", 1, "Recebedor4", "123.456.789-4", "2017-11-04 04:10:04"); 

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("P5", 1, "Recebedor5", "123.456.789-5", "2017-11-05 06:20:05"); 

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("P6", 1, "Recebedor6", "123.456.789-6", "2017-11-06 06:30:06"); 

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("P7", 1, "Recebedor7", "123.456.789-7", "2017-11-07 08:10:07"); 

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("P8", 1, "Recebedor8", "123.456.789-8", "2017-11-08 08:20:08"); 

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("P9", 1, "Recebedor9", "123.456.789-9", "2017-11-09 10:30:09"); 

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("P10", 1, "Recebedor10", "123.456.789-10", "2017-11-10 10:50:10"); 


CREATE TABLE user (  
	id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
	login VARCHAR(300),
	password VARCHAR(300)
);

insert into user (login, password) 
values ("user1", "password1"); 

insert into user (login, password) 
values ("user2", "password2"); 

insert into user (login, password) 
values ("user3", "password3"); 
