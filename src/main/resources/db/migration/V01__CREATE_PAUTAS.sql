CREATE TABLE IF NOT EXISTS pautas (
	id bigint not null auto_increment,
	titulo varchar(150) not null,
	data_criacao date not null,
	status int,
	liberar_votacao int,
	primary key(id)
);
