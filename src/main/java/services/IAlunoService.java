package services;

import models.Sexo;
import models.entitys.Aluno;

import java.time.LocalDate;
import java.util.List;

public interface IAlunoService {
    int save(long cpf, String nome, Sexo sexo, LocalDate dataNascimento);

    int update(long cpf, String nome, Sexo sexo, LocalDate dataNascimento);

    int delete(long cpf);

    Aluno get(long cpf);

    List<Aluno> getAll();
}
