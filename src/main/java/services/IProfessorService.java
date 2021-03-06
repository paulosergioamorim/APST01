package services;

import models.Sexo;
import models.entitys.Professor;

import java.time.LocalDate;
import java.util.List;

public interface IProfessorService {
    int save(long cpf, String nome, Sexo sexo, LocalDate dataNascimento, String titulacao);

    int update(long cpf, String nome, Sexo sexo, LocalDate dataNascimento, String titulacao);

    int delete(long cpf);

    Professor get(long cpf);

    List<Professor> getAll();
}
