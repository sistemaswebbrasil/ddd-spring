CREATE SEQUENCE IF NOT EXISTS pessoa_id_seq start with 1  ;

create table pessoa (
    codigo bigint default pessoa_id_seq.nextval ,
    cpf varchar(11) not null,
    nome varchar(80) not null,
    primary key (codigo)
)