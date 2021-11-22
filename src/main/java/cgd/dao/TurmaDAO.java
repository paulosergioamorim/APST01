package cgd.dao;

import cdp.Turma;

import java.util.List;

public class TurmaDAO extends DAO<Turma,Long> {
    public TurmaDAO(String url) { super(url); }

    @Override
    public void save(Turma turma) {
        this.session.save(turma);
        this.commit();
    }

    @Override
    public void update(Turma turma) {
        this.session.update(turma);
        this.commit();
    }

    @Override
    public void delete(Turma turma) {
        this.session.delete(turma);
        this.commit();
    }

    @Override
    public Turma find(Long key) { return this.session.get(Turma.class, key); }

    @Override
    public boolean exists(Long key) { return this.find(key) != null; }

    @Override
    public long count() { return this.findAll().size(); }

    @SuppressWarnings("unchecked")
    @Override
    public List<Turma> findAll() { return (List<Turma>) this.session.createQuery("from Turma").list(); }
}
