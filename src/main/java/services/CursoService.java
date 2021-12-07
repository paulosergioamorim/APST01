package services;

import database.CursoDAO;
import models.entitys.Curso;

import java.util.List;

import static models.Format.cursoNomePattern;
import static models.Estado.FECHADA;

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
        if (!dao.exists(id))
            return 1;
        Curso curso = dao.find(id);

        nome = nome == null ? curso.getNome() : nome;
        sigla = sigla == null ? curso.getSigla() : sigla;
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
        if (!dao.exists(id))
            return 1;
        Curso curso = dao.load(id);
        if (curso.getTurmas().stream().anyMatch(t -> t.getEstado() != FECHADA))
            return 2;
        dao.delete(curso);
        return 0;
    }

    @Override
    public Curso get(int id) { return dao.find(id); }

    @Override
    public List<Curso> getAll() { return dao.findAll(); }
}
