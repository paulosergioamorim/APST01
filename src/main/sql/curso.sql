create table public.curso
(
    id           integer     not null
        primary key,
    cargahoraria integer     not null,
    nome         varchar(45) not null,
    sigla        varchar(5)  not null
        constraint uk_ncyvnixbg7ihtkq9yncjgulxr
            unique
);

alter table public.curso
    owner to postgres;

