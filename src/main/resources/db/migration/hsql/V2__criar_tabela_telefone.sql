create table telefone (
   codigo bigint auto_increment primary key ,
   ddd VARCHAR(2) NOT NULL,
   numero varchar(9) not null,
   codigo_pessoa bigint,
   primary key (codigo),
   FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
);