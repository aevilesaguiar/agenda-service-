	CREATE TABLE agenda(
    id serial primary key,
    descricao varchar (255),
    data_hora timestamp,
    data_criacao timestamp,
    evento_id integer,
    CONSTRAINT fk_agenda_evento FOREIGN KEY(evento_id) REFERENCES evento(id)
    	);


