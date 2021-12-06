create table turma
(
    id              varchar(255) not null
        primary key,
    datafim         date         not null,
    datainicio      date         not null,
    estado          varchar(255) not null,
    horario         time         not null,
    limite          integer      not null,
    curso_id        integer      not null
        constraint fkfh64j0pkcjkjl9h6vbakpwt31
            references public.curso,
    responsavel_cpf bigint       not null
        constraint fk150aji70vpujxe4rn2gdwjnpb
            references public.professor
);

alter table public.turma
    owner to postgres;

