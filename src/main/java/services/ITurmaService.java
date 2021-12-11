package services;

import models.entitys.Curso;
import models.entitys.Professor;
import models.entitys.Turma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ITurmaService {
    int save(int id,
             LocalDate dataInicio,
             LocalDate dataFim,
             LocalTime horario,
             int limite,
             Curso curso,
             Professor responsavel);
    int update(int id,
               LocalDate dataInicio,
               LocalDate dataFim,
               LocalTime horario,
               int limite,
               Curso curso,
               Professor responsavel);
    int close(int id);
    int delete(int id);
    Turma get(int id);
    List<Turma> getAll();
}
