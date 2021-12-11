package datasources;

import models.entitys.Turma;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class TurmaDAO extends DAO<Turma, Integer> {
    public TurmaDAO(final Configuration configuration, @NotNull final Class<Turma> entity) {
        super(configuration, entity);
    }

    public Turma load(final int id) {
        this.open();
        try {
            final String hql = "from Turma fetch all properties where id = :id";
            return (Turma) this.session.createQuery(hql)
                    .setParameter("id",id)
                    .uniqueResult();
        } finally {
            this.close();
        }
    }
}
