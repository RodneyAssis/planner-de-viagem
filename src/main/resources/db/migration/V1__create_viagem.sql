create table if not exists viagem
(
    id uuid not null primary key,
    confirmacao boolean not null,
    data_fim timestamp(6) not null,
    data_inicio timestamp(6) not null,
    destino varchar(255) not null,
    email_proprietario varchar(255) not null,
    nome_proprietario varchar(255) not null
);