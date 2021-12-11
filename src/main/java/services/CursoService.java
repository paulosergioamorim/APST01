package services;

import datasources.CursoDAO;
import models.entitys.Curso;

import java.util.List;

import static models.Format.cursoNomePattern;
import static models.Estado.FECHADA;

public record CursoService(CursoDAO dao) implements ICursoService {
    @Override
    public int save(final int id, final String nome, final String sigla, final int cargaHoraria) {
        if (this.dao.exists(id))
            return 1;
        if (!nome.matches(cursoNomePattern.pattern()))
            return 2;
        if (this.dao.existsBySigla(sigla))
            return 3;
        if (sigla.length() > 5)
            return 4;
        if (cargaHoraria < 0)
            return 5;
        final Curso curso = new Curso(id, nome, sigla, cargaHoraria);
        this.dao.save(curso);
        return 0;
    }

    @Override
    public int update(final int id, String nome, String sigla, int cargaHoraria) {
        if (! this.dao.exists(id))
            return 1;
        Curso curso = this.dao.get(id);

        nome = nome == null ? curso.getNome() : nome;
        sigla = sigla == null ? curso.getSigla() : sigla;
        cargaHoraria = cargaHoraria == 0 ? curso.getCargaHoraria() : cargaHoraria;

        if (!nome.matches(cursoNomePattern.pattern()))
            return 2;
        if (this.dao.existsBySigla(sigla))
            return 3;
        if (sigla.length() > 5)
            return 4;
        if (cargaHoraria <= 0)
            return 5;

        curso = new Curso(id, nome, sigla, cargaHoraria);
        this.dao.update(curso);
        return 0;
    }

    @Override
    public int delete(final int id) {
        if (! this.dao.exists(id))
            return 1;
        final Curso curso = this.dao.load(id);
        if (curso.getTurmas().stream().anyMatch(t -> t.getEstado() != FECHADA))
            return 2;
        this.dao.delete(curso);
        return 0;
    }

    @Override
    public Curso get(final int id) { return this.dao.get(id); }

    @Override
    public List<Curso> getAll() { return this.dao.toList(); }
}
