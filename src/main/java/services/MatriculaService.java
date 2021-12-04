package services;

import database.MatriculaDAO;
import models.entitys.Aluno;
import models.entitys.Matricula;
import models.entitys.Turma;

import java.time.LocalDate;
import java.util.List;

public record MatriculaService(MatriculaDAO dao) implements IMatriculaService {
    @Override
    public int save(Aluno aluno, Turma turma, LocalDate dataMatricula, double nota) {
        return 0;
    }

    @Override
    public int update(Aluno aluno, Turma turma, LocalDate dataMatricula, double nota) {
        return 0;
    }

    @Override
    public int delete(Aluno aluno, Turma turma) {
        return 0;
    }

    @Override
    public Matricula get(Aluno aluno, Turma turma) {
        return null;
    }

    @Override
    public List<Matricula> getAll() {
        return null;
    }
}
