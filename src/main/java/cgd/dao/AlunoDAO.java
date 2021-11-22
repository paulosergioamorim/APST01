package cgd.dao;

import cdp.Aluno;

import java.util.List;

public class AlunoDAO extends DAO<Aluno, Integer> {
    public AlunoDAO(String url) { super(url); }

    @Override
    public void save(Aluno aluno) {
        this.session.save(aluno);
        this.commit();
    }

    @Override
    public void update(Aluno aluno) {
        this.session.update(aluno);
        this.commit();
    }

    @Override
    public void delete(Aluno aluno) {
        this.session.delete(aluno);
        this.commit();
    }

    @Override
    public Aluno find(Integer key) { return this.session.get(Aluno.class, key); }

    @Override
    public boolean exists(Integer key) { return this.find(key) != null; }

    @Override
    public long count() { return this.findAll().size(); }

    @SuppressWarnings("unchecked")
    @Override
    public List<Aluno> findAll() { return (List<Aluno>) this.session.createQuery("from Aluno").list(); }
}
