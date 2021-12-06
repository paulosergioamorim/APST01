create table public.aluno
(
    cpf            bigint      not null
        primary key,
    datanascimento date        not null,
    nome           varchar(45) not null,
    sexo           char        not null
);

alter table public.aluno
    owner to postgres;

