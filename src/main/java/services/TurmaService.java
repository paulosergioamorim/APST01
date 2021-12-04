package services;

import database.TurmaDAO;
import models.entitys.Curso;
import models.entitys.Professor;
import models.entitys.Turma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record TurmaService(TurmaDAO dao) implements ITurmaService {
    @Override
    public int save(int id,
                    LocalDate dataInicio,
                    LocalDate dataFim,
                    LocalTime horario,
                    int limite,
                    boolean fechada,
                    Curso curso,
                    Professor professor) {
        return 0;
    }

    @Override
    public int update(int id,
                      LocalDate dataInicio,
                      LocalDate dataFim,
                      LocalTime horario,
                      int limite,
                      boolean fechada,
                      Curso curso, Professor professor) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public Turma get(int id) {
        return null;
    }

    @Override
    public List<Turma> getAll() {
        return null;
    }
}
