package database;

import models.entitys.Curso;

public class CursoDAO extends DAO<Curso, Integer> {

    /**
     * @param url Hibernate Configuration File URL
     */
    public CursoDAO(String url) { super(url, Curso.class); }

    public boolean existsBySigla(String sigla) {
        this.open();
        try {
            String sql = "from Curso where sigla = :sigla";
            return session.createQuery(sql)
                    .setParameter("sigla", sigla)
                    .uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.close();
        }
    }
}
