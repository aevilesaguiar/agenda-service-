











## Tabelas

CREATE TABLE agenda(
id serial primary key,
descricao varchar (255),
data_hora timestamp,
data_criacao timestamp,
paciente_id integer,
CONSTRAINT fk_agenda_paciente FOREIGN KEY(paciente_id) REFERENCES paciente(id)
);



CREATE TABLE paciente(
id serial primary key,
nome varchar (50),
sobrenome varchar (100),
cpf varchar(50),
email varchar (100)
);