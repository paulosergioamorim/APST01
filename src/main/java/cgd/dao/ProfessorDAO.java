package cgd.dao;

import cdp.Professor;

import java.util.List;

public class ProfessorDAO extends DAO<Professor, Long> {
    public ProfessorDAO(String url) { super(url); }

    @Override
    public void save(Professor professor) {
        this.session.save(professor);
        this.commit();
    }

    @Override
    public void update(Professor professor) {
        this.session.update(professor);
        this.commit();
    }

    @Override
    public void delete(Professor professor) {
        this.session.delete(professor);
        this.commit();
    }

    @Override
    public Professor find(Long key) { return this.session.get(Professor.class, key); }

    @Override
    public boolean exists(Long key) { return this.find(key) != null; }

    @Override
    public long count() { return this.findAll().size(); }

    @SuppressWarnings("unchecked")
    @Override
    public List<Professor> findAll() { return (List<Professor>) this.session.createQuery("from Professor").list(); }
}
