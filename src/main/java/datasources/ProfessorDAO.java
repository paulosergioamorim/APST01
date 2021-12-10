package datasources;

import models.entitys.Professor;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class ProfessorDAO extends DAO<Professor, Long> {
    public ProfessorDAO(Configuration configuration, @NotNull Class<Professor> entity) {
        super(configuration, entity);
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
