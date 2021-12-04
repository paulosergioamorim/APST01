package services;

import database.CursoDAO;
import models.entitys.Curso;

import java.util.List;

import static models.Format.cursoNomePattern;

public record CursoService(CursoDAO dao) implements ICursoService {
    @Override
    public int save(int id, String nome, String sigla, int cargaHoraria) {
        if (dao.exists(id))
            return 1;
        if (! nome.matches(cursoNomePattern.pattern()))
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
        if (! dao.exists(id))
            return 1;
        if (nome != null && ! nome.matches(cursoNomePattern.pattern()))
            return 2;
        if (sigla != null && dao.existsBySigla(sigla))
            return 3;
        if (sigla != null && sigla.length() > 5)
            return 4;
        if (cargaHoraria != - 1 && cargaHoraria < 0)
            return 5;
        Curso curso = dao.find(id);
        if (nome != null) curso.setNome(nome);
        if (sigla != null) curso.setSigla(sigla);
        if (cargaHoraria != - 1) curso.setCargaHoraria(cargaHoraria);
        dao.update(curso);
        return 0;
    }

    @Override
    public int delete(int id) {
        Curso curso = dao.find(id);
        if (curso == null)
            return 1;
        if (curso.getTurmas().stream().anyMatch(t -> ! t.isFechada()))
            return 2;
        dao.delete(curso);
        return 0;
    }

    @Override
    public Curso get(int id) { return dao.find(id); }

    @Override
    public List<Curso> getAll() { return dao.findAll(); }
}
