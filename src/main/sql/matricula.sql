create table public.matricula
(
    datamatricula date             not null,
    nota          double precision not null,
    aluno_cpf     bigint           not null
        constraint fkpsiva8m5pc1s0y034t2vw144x
            references public.aluno,
    turma_id      varchar(255)     not null
        constraint fk3h6r6k367clhn67ibyd7e626f
            references public.turma,
    primary key (aluno_cpf, turma_id)
);

alter table public.matricula
    owner to postgres;

