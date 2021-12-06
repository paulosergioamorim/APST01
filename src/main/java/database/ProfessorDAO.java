package database;

import models.entitys.Professor;

public class ProfessorDAO extends DAO<Professor, Long> {
    /**
     * @param url Hibernate Configuration File URL
     */
    public ProfessorDAO(String url) {
        super(url, Professor.class);
    }

    public boolean isActive(Professor professor) {
        this.open();
        try {
            String sql =
            """
                    from Turma
                    where responsavel = :professor
                    and estado != 'Fechada'
                    and dataInicio < current_date
                    and dataFim > current_date
                    """;
            return !session.createQuery(sql)
                    .setParameter("professor", professor)
                    .list().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.close();
        }
    }
}
