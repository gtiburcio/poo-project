-- create perfil

create table perfil(
id_perfil bigint primary key auto_increment,
descricao varchar(100) not null
);

insert into perfil(descricao) values('Secretária'), ('Médico');
