package cgd.dao;

import cdp.Curso;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CursoDAO extends DAO<Curso, String> {
    public CursoDAO(String url) { super(url); }

    @Override
    public void save(Curso curso) {
        this.session.save(curso);
        this.commit();
    }

    @Override
    public void update(Curso curso) {
        this.session.update(curso);
        this.commit();
    }

    @Override
    public void delete(Curso curso) {
        this.session.delete(curso);
        this.commit();
    }

    @Override
    public Curso find(String key) { return this.session.get(Curso.class, key); }

    @Override
    public boolean exists(String key) { return this.find(key) != null; }

    @Override
    public long count() { return this.findAll().size(); }

    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> findAll() { return (List<Curso>) this.session.createQuery("from Curso").list(); }

}
