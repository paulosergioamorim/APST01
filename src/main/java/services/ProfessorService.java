package services;

import database.ProfessorDAO;
import models.Sexo;
import models.entitys.Professor;

import java.time.LocalDate;
import java.util.List;

public record ProfessorService(ProfessorDAO dao) implements IProfessorService {
    @Override
    public int save(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        return 0;
    }

    @Override
    public int update(long cpf, String nome, Sexo sexo, LocalDate dataNascimento) {
        return 0;
    }

    @Override
    public int delete(long cpf) {
        return 0;
    }

    @Override
    public Professor get(long cpf) {
        return null;
    }

    @Override
    public List<Professor> getAll() {
        return null;
    }
}
