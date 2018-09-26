CREATE SEQUENCE IF NOT EXISTS endereco_id_seq start with 1  ;

create table endereco (
   codigo bigint default endereco_id_seq.nextval ,
    bairro varchar(255),
    cidade varchar(255),
    complemento varchar(255),
    estado varchar(255),
    logradouro varchar(255),
    numero integer,
    codigo_pessoa bigint,
    primary key (codigo),
    FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
);
