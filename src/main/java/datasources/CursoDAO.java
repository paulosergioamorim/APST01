package datasources;

import models.entitys.Curso;
import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class CursoDAO extends DAO<Curso, Integer> {
    public CursoDAO(Configuration configuration, @NotNull Class<Curso> entity) {
        super(configuration, entity);
    }

    public Curso load(Integer id) {
        this.open();
        try {
            String hql = "from Curso where id = :id";
            Curso curso = (Curso) session.createQuery(hql)
                    .setParameter("id", id)
                    .uniqueResult();
            if (curso == null)
                return null;
            Hibernate.initialize(curso.getTurmas());
            return curso;
        } finally {
            this.close();
        }
    }

    public boolean existsBySigla(String sigla) {
        this.open();
        try {
            String sql = "from Curso where sigla = :sigla";
            return session.createQuery(sql)
                    .setParameter("sigla", sigla)
                    .uniqueResult() != null;
        } finally {
            this.close();
        }
    }
}
