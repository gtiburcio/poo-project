create table agendamento(
    id_agendamento bigint primary key auto_increment,
    data date not null,
    hora time,
    duracao int default 30,
    id_paciente bigint,
    id_medico bigint,
    foreign key (id_paciente) references paciente(id_paciente),
    foreign key (id_medico) references medico(id_medico)
);
