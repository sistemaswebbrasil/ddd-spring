create table pessoa (
    codigo bigint auto_increment primary key ,
    cpf varchar(11) not null,
    nome varchar(80) not null,
    primary key (codigo)
)