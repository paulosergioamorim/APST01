package database;

import models.entitys.Turma;
import org.hibernate.Hibernate;

public class TurmaDAO extends DAO<Turma, String> {
    /**
     * @param url Hibernate Configuration File URL
     */
    public TurmaDAO(String url) {
        super(url, Turma.class);
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
