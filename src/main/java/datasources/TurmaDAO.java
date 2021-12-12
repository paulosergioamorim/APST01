package datasources;

import models.entitys.Turma;
import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class TurmaDAO extends DAO<Turma, Integer> {
    public TurmaDAO(Configuration configuration, @NotNull Class<Turma> entity) {
        super(configuration, entity);
    }

    public Turma load(int id) {
        this.open();
        try {
            String hql = "from Turma where id = :id";
            Turma turma = (Turma) session.createQuery(hql)
                    .setParameter("id",id)
                    .uniqueResult();
            Hibernate.initialize(turma.getMatriculas());
            return turma;
        } finally {
            this.close();
        }
    }
}
