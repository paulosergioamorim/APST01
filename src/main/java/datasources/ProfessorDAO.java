package datasources;

import models.entitys.Professor;
import models.entitys.Turma;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProfessorDAO extends DAO<Professor, Long> {
    public ProfessorDAO(Configuration configuration, @NotNull Class<Professor> entity) {
        super(configuration, entity);
    }

    @SuppressWarnings("unchecked")
    public boolean containsTurmas(Professor professor) {
        this.open();
        try {
            String hql = "from Turma where responsavel = :professor";
            List<Turma> turmas = session.createQuery(hql)
                    .setParameter("professor", professor)
                    .getResultList();
            return !turmas.isEmpty();
        } finally {
            this.close();
        }
    }
}
