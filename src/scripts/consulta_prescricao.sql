create table consulta(
    id_consulta bigint primary key auto_increment,
    data date not null,
    hora time not null,
    id_agendamento bigint,
    foreign key (id_agendamento) references agendamento(id_agendamento)
);

create table prescricao(
    id_prescricao bigint primary key auto_increment,
    descricao varchar(255) not null,
    id_consulta bigint not null,
    foreign key (id_consulta) references consulta(id_consulta)
);
