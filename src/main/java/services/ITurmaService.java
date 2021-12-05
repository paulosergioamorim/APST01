package services;

import models.entitys.Curso;
import models.entitys.Professor;
import models.entitys.Turma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ITurmaService {
    int save(String id,
             LocalDate dataInicio,
             LocalDate dataFim,
             LocalTime horario,
             int limite,
             Curso curso,
             Professor responsavel);
    int update(String id,
               LocalDate dataInicio,
               LocalDate dataFim,
               LocalTime horario,
               int limite,
               Curso curso,
               Professor responsavel);
    int delete(String id);
    Turma get(String id);
    List<Turma> getAll();
}
