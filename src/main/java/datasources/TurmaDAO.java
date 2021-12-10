package datasources;

import models.entitys.Turma;
import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class TurmaDAO extends DAO<Turma, String> {
    public TurmaDAO(Configuration configuration, @NotNull Class<Turma> entity) {
        super(configuration, entity);
    }

    public Turma load(String key) {
        this.open();
        try {
            Turma turma = session.load(Turma.class, key);
            Hibernate.initialize(turma.getMatriculas());
            return turma;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.close();
        }
    }
}
