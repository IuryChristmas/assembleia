CREATE TABLE IF NOT EXISTS associados (
	id bigint not null auto_increment,
	nome varchar(200) not null,
	cpf varchar(14) not null,
	primary key(id)
);
