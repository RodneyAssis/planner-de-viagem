create table if not exists viagens_convidados
(
    fk_viagem uuid not null
        constraint fkh05w48da02f0x2vx9wtds2fkx
            references viagem,
    fk_convidado bigint not null
        constraint fkr91n3fkce2gv46yj5yr3rpjko
            references convidado
);