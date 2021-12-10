package services;

import models.MatriculaID;
import models.entitys.Aluno;
import models.entitys.Matricula;
import models.entitys.Turma;

import java.time.LocalDate;
import java.util.List;

public interface IMatriculaService {
    int save(MatriculaID matriculaID, LocalDate dataMatricula, double nota);
    int update(MatriculaID matriculaID, LocalDate dataMatricula, double nota);
    int delete(MatriculaID matriculaID);
    Matricula get(MatriculaID matriculaID);
    List<Matricula> getAll();
}
