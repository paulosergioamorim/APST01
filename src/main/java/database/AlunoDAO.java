package database;

import models.entitys.Aluno;

public class AlunoDAO extends DAO<Aluno, Long> {

    /**
     * @param url Hibernate Configuration File URL
     */
    public AlunoDAO(String url) { super(url, Aluno.class); }
}
