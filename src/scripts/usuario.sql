-- create usuario

create table usuario(
id_usuario bigint primary key auto_increment,
nome  varchar(200) not null,
login varchar(200) not null,
senha varchar(200) not null,
id_perfil bigint,
foreign key (id_perfil) references perfil(id_perfil)
);

insert into usuario(nome, login, senha, id_perfil) values ('admin', 'admin', '123', 1);

alter table usuario modify login varchar(200) unique not null;
