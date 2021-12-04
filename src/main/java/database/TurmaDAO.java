package database;

import models.entitys.Turma;

public class TurmaDAO extends DAO<Turma, Integer> {
    /**
     * @param url Hibernate Configuration File URL
     */
    public TurmaDAO(String url) {
        super(url, Turma.class);
    }
}
