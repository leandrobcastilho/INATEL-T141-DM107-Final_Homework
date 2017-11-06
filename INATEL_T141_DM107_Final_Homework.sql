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

CREATE TABLE user (  
	id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
	login VARCHAR(300),
	password VARCHAR(300)
);

SELECT * FROM Entregas WHERE Entregas.id=1;

insert into entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) 
values ("Aluno 1", "aluno1@inatel.br", "(35)8888-9999"); 

insert into entregas (nome, email, cel) 
values ("Aluno 2", "aluno2@inatel.br", "(35)7777-9999"); 

insert into entregas (nome, email, cel) 
values ("Aluno 3", "aluno3@inatel.br", "(35)6666-9999"); 

insert into entregas (nome, email, cel) 
values ("Aluno 4", "aluno4@inatel.br", "(35)2222-9999"); 