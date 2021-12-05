package services;

import models.entitys.Curso;
import models.entitys.Professor;
import models.entitys.Turma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ITurmaService {
    int save(String code,
             LocalDate dataInicio,
             LocalDate dataFim,
             LocalTime horario,
             int limite,
             boolean fechada,
             Curso curso,
             Professor responsavel);
    int update(String code,
               LocalDate dataInicio,
               LocalDate dataFim,
               LocalTime horario,
               int limite,
               boolean fechada,
               Curso curso,
               Professor responsavel);
    int delete(String code);
    Turma get(String code);
    List<Turma> getAll();
}
