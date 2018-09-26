CREATE SEQUENCE IF NOT EXISTS telefone_id_seq start with 1  ;

create table telefone (
   codigo bigint default telefone_id_seq.nextval ,
   ddd VARCHAR(2) NOT NULL,
   numero varchar(9) not null,
   codigo_pessoa bigint,
   primary key (codigo),
   FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
);