create table endereco (
   codigo bigint auto_increment primary key ,
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
