package services;

import datasources.TurmaDAO;
import models.entitys.Curso;
import models.entitys.Professor;
import models.entitys.Turma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static models.Estado.FECHADA;

public record TurmaService(TurmaDAO dao) implements ITurmaService {
    @Override
    public int save(int id,
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
    public int update(int id,
                      LocalDate dataInicio,
                      LocalDate dataFim,
                      LocalTime horario,
                      int limite,
                      Curso curso,
                      Professor responsavel) {
        if (!dao.exists(id))
            return 1;
        Turma turma = dao.get(id);

        dataInicio = dataInicio == null ? turma.getDataInicio() : dataInicio;
        dataFim = dataFim == null ? turma.getDataFim() : dataFim;
        horario = horario == null ? turma.getHorario() : horario;
        limite = limite == 0 ? turma.getLimite() : limite;
        curso = curso == null ? turma.getCurso() : curso;
        responsavel = responsavel == null ? turma.getResponsavel() : responsavel;

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

        turma = new Turma(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        dao.update(turma);
        return 0;
    }

    @Override
    public int delete(int id) {
        if (!dao.exists(id))
            return 1;
        Turma turma = dao.load(id);
        if (turma.getEstado() != FECHADA)
            return 2;
        if (!turma.getMatriculas().isEmpty())
            return 3;
        dao.delete(turma);
        return 0;
    }

    @Override
    public Turma get(int id) { return dao.get(id); }

    @Override
    public List<Turma> getAll() { return dao.toList(); }
}
