package datasources;

import models.entitys.Curso;
import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class CursoDAO extends DAO<Curso, Integer> {
    public CursoDAO(final Configuration configuration, @NotNull final Class<Curso> entity) {
        super(configuration, entity);
    }

    public Curso load(final Integer id) {
        this.open();
        try {
            final String hql = "from Curso fetch all properties where id = :id";
            return (Curso) this.session.createQuery(hql)
                    .setParameter("id", id)
                    .uniqueResult();
        } finally {
            this.close();
        }
    }

    public boolean existsBySigla(final String sigla) {
        this.open();
        try {
            final String sql = "from Curso where sigla = :sigla";
            return this.session.createQuery(sql).setParameter("sigla", sigla).uniqueResult() != null;
        } finally {
            this.close();
        }
    }
}
