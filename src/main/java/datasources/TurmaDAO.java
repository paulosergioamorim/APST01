package datasources;

import models.entitys.Turma;
import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class TurmaDAO extends DAO<Turma, Integer> {
    public TurmaDAO(Configuration configuration, @NotNull Class<Turma> entity) {
        super(configuration, entity);
    }

    /**
     * Load Turma object with your matriculas
     * @param key id of the object
     * @return loaded object
     */
    public Turma load(int key) {
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
