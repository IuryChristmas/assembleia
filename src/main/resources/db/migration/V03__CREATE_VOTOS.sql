CREATE TABLE IF NOT EXISTS votos (
	id bigint not null auto_increment,
	voto_associado int not null,
	id_pauta bigint not null,
	id_associado bigint not null,
	primary key(id),
	foreign key(id_pauta) references pautas(id),
	foreign key(id_associado) references associados(id)
);
