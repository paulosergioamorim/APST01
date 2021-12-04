package database;

import models.entitys.Professor;

public class ProfessorDAO extends DAO<Professor, Long> {
    /**
     * @param url Hibernate Configuration File URL
     */
    public ProfessorDAO(String url) {
        super(url, Professor.class);
    }
}
