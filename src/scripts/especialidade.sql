create table especialidade(
    id_especialidade bigint primary key auto_increment,
    nome varchar(255) not null unique,
    descricao varchar(255)
);

insert into especialidade(nome, descricao) values ('Pediatra', '');
