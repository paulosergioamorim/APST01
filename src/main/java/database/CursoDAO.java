package database;

import models.entitys.Curso;
import org.hibernate.Hibernate;

public class CursoDAO extends DAO<Curso, Integer> {

    /**
     * @param url Hibernate Configuration File URL
     */
    public CursoDAO(String url) { super(url, Curso.class); }

    public Curso load(Integer key) {
        this.open();
        try {
            Curso curso = session.load(Curso.class, key);
            Hibernate.initialize(curso.getTurmas());
            return curso;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.close();
        }
    }

    public boolean existsBySigla(String sigla) {
        this.open();
        try {
            String sql = "from Curso where sigla = :sigla";
            return session.createQuery(sql).setParameter("sigla", sigla).uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.close();
        }
    }
}
