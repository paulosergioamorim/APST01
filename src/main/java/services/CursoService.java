package services;

import datasources.CursoDAO;
import models.entitys.Curso;

import java.util.List;

import static models.Estado.FECHADA;
import static models.Format.cursoNomePattern;

public record CursoService(CursoDAO dao) implements ICursoService {
    @Override
    public int save(int id, String nome, String sigla, int cargaHoraria) {
        if (dao.exists(id))
            return 1;
        if (!nome.matches(cursoNomePattern.pattern()))
            return 2;
        if (dao.existsBySigla(sigla))
            return 3;
        if (sigla.length() > 5)
            return 4;
        if (cargaHoraria < 0)
            return 5;
        Curso curso = new Curso(id, nome, sigla, cargaHoraria);
        dao.save(curso);
        return 0;
    }

    @Override
    public int update(int id, String nome, String sigla, int cargaHoraria) {
        Curso curso = dao.get(id);
        if (curso == null)
            return 1;

        nome = nome.isEmpty() ? curso.getNome() : nome;
        sigla = sigla.isEmpty() ? curso.getSigla() : sigla;
        cargaHoraria = cargaHoraria == 0 ? curso.getCargaHoraria() : cargaHoraria;

        if (!nome.matches(cursoNomePattern.pattern()))
            return 2;
        if (dao.existsBySigla(sigla))
            return 3;
        if (sigla.length() > 5)
            return 4;
        if (cargaHoraria <= 0)
            return 5;

        curso = new Curso(id, nome, sigla, cargaHoraria);
        dao.update(curso);
        return 0;
    }

    @Override
    public int delete(int id) {
        Curso curso = dao.load(id);
        if (curso == null)
            return 1;
        if (!curso.getTurmas().isEmpty())
            return 2;
        dao.delete(curso);
        return 0;
    }

    @Override
    public Curso get(int id) { return dao.get(id); }

    @Override
    public List<Curso> getAll() { return dao.toList(); }
}
