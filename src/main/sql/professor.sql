create table public.professor
(
    cpf            bigint      not null
        primary key,
    datanascimento date        not null,
    nome           varchar(45) not null,
    sexo           char        not null,
    titulacao      varchar(25) not null
);

alter table public.professor
    owner to postgres;

