create table if not exists atividade
(
    id integer not null primary key,
    data_inicio timestamp(6) not null,
    titulo varchar(255) not null,
    viagem_id uuid
        constraint fksjwiohigdgxadx6mva4dvqyeu
            references viagem on delete cascade
);