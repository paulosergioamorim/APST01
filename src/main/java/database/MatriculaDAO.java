package database;

import models.MatriculaID;
import models.entitys.Matricula;

public class MatriculaDAO extends DAO<Matricula, MatriculaID> {
    /**
     * @param url    Hibernate Configuration File URL
     */
    public MatriculaDAO(String url) {
        super(url, Matricula.class);
    }
}
