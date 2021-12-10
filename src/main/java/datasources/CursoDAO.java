package datasources;

import models.entitys.Curso;
import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class CursoDAO extends DAO<Curso, Integer> {
    public CursoDAO(Configuration configuration, @NotNull Class<Curso> entity) {
        super(configuration, entity);
    }

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
