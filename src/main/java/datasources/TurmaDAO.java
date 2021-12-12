package datasources;

import models.entitys.Turma;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class TurmaDAO extends DAO<Turma, Integer> {
    public TurmaDAO(Configuration configuration, @NotNull Class<Turma> entity) {
        super(configuration, entity);
    }

    public Turma load(int id) {
        this.open();
        try {
            final String hql = "from Turma fetch all properties where id = :id";
            return (Turma) session.createQuery(hql)
                    .setParameter("id",id)
                    .uniqueResult();
        } finally {
            this.close();
        }
    }
}
