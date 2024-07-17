create table if not exists convidado
(
    id bigserial not null primary key,
    email varchar(255) not null,
    is_confirmado boolean,
    nome varchar(255)
);