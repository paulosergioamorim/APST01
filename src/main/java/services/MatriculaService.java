package services;

import datasources.MatriculaDAO;
import models.MatriculaID;
import models.entitys.Aluno;
import models.entitys.Matricula;
import models.entitys.Turma;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

import static models.Estado.MATRICULAS_ABERTAS;

public record MatriculaService(MatriculaDAO dao) implements IMatriculaService {
    @Override
    public int save(@NotNull final Aluno aluno,
                    @NotNull final Turma turma,
                    final LocalDate dataMatricula,
                    final Double nota) {
        final MatriculaID matriculaID = new MatriculaID(aluno.getCpf(), turma.getId());
        if (this.dao.exists(matriculaID)) return 1;
        if (dataMatricula.isAfter(LocalDate.now())) return 2;
        if (dataMatricula.isAfter(turma.getDataInicio())) return 3;
        if (turma.getEstado() != MATRICULAS_ABERTAS) return 4;
        final Matricula matricula = new Matricula(matriculaID, dataMatricula, nota);
        this.dao.save(matricula);
        return 0;
    }

    @Override
    public int update(@NotNull final Aluno aluno, @NotNull final Turma turma, LocalDate dataMatricula, Double nota) {
        final MatriculaID matriculaID = new MatriculaID(aluno.getCpf(), turma.getId());
        if (!this.dao.exists(matriculaID)) return 1;
        Matricula matricula = this.dao.get(matriculaID);

        dataMatricula = dataMatricula == null ? matricula.getDataMatricula() : dataMatricula;
        nota = nota == null ? matricula.getNota() : nota;

        if (dataMatricula.isAfter(LocalDate.now())) return 2;
        if (dataMatricula.isAfter(matricula.getTurma().getDataInicio())) return 3;
        if (nota != null && (nota < 0 || nota > 10)) return 4;

        matricula = new Matricula(matriculaID, dataMatricula, nota);
        this.dao.update(matricula);
        return 0;
    }

    @Override
    public int delete(final MatriculaID matriculaID) {
        final Matricula matricula = this.dao.get(matriculaID);
        if (matricula == null) return 1;
        if (matricula.getNota() != null) return 2;
        this.dao.delete(matricula);
        return 0;
    }

    @Override
    public Matricula get(final MatriculaID matriculaID) { return this.dao.get(matriculaID); }

    @Override
    public List<Matricula> getAll() { return this.dao.toList(); }
}
