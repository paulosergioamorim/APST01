package services;

import database.TurmaDAO;
import models.entitys.Curso;
import models.entitys.Professor;
import models.entitys.Turma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static models.Estado.*;

public record TurmaService(TurmaDAO dao) implements ITurmaService {
    @Override
    public int save(String id,
                    LocalDate dataInicio,
                    LocalDate dataFim,
                    LocalTime horario,
                    int limite,
                    Curso curso,
                    Professor professor) {
        if (dao.exists(id))
            return 1;
        if (dataInicio.isAfter(dataFim))
            return 2;
        if (dataInicio.isBefore(LocalDate.now()))
            return 3;
        if (dataFim.isBefore(LocalDate.now()))
            return 4;
        if (dataInicio.isEqual(dataFim))
            return 5;
        if (limite <= 0)
            return 6;
        Turma turma = new Turma(id, dataInicio, dataFim, horario, limite, curso, professor);
        dao.save(turma);
        return 0;
    }

    @Override
    public int update(String id,
                      LocalDate dataInicio,
                      LocalDate dataFim,
                      LocalTime horario,
                      int limite,
                      Curso curso,
                      Professor professor) {
        if (!dao.exists(id))
            return 1;
        if (dataInicio != null && dataInicio.isAfter(dataFim))
            return 2;
        if (dataInicio != null && dataInicio.isBefore(LocalDate.now()))
            return 3;
        if (dataFim != null && dataFim.isBefore(LocalDate.now()))
            return 4;
        if (dataInicio != null && dataInicio.isEqual(dataFim))
            return 5;
        if (limite != -1 && limite <= 0)
            return 6;
        Turma turma = dao.find(id);
        if (dataInicio != null)
            turma.setDataInicio(dataInicio);
        if (dataFim != null)
            turma.setDataFim(dataFim);
        if (horario != null)
            turma.setHorario(horario);
        if (limite != -1)
            turma.setLimite(limite);
        if (curso != null)
            turma.setCurso(curso);
        if (professor != null)
            turma.setResponsavel(professor);
        dao.update(turma);
        return 0;
    }

    @Override
    public int delete(String id) {
        Turma turma = dao.find(id);
        if (turma == null)
            return 1;
        if (turma.getEstado() != FECHADA)
            return 2;
        dao.delete(turma);
        return 0;
    }

    @Override
    public Turma get(String id) { return dao.find(id); }

    @Override
    public List<Turma> getAll() { return dao.findAll(); }
}
