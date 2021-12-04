package services;

import models.entitys.Aluno;
import models.entitys.Matricula;
import models.entitys.Turma;

import java.time.LocalDate;
import java.util.List;

public interface IMatriculaService {
    int save(Aluno aluno, Turma turma, LocalDate dataMatricula, double nota);
    int update(Aluno aluno, Turma turma, LocalDate dataMatricula, double nota);
    int delete(Aluno aluno, Turma turma);
    Matricula get(Aluno aluno, Turma turma);
    List<Matricula> getAll();
}
