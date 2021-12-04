package database;

import models.entitys.Matricula;
import models.MatriculaID;

public class MatriculaDAO extends DAO<Matricula, MatriculaID> {
    /**
     * @param url    Hibernate Configuration File URL
     */
    public MatriculaDAO(String url) {
        super(url, Matricula.class);
    }
}
