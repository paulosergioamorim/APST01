package datasources;

import models.entitys.Professor;
import models.entitys.Turma;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static models.Estado.FECHADA;

public class ProfessorDAO extends DAO<Professor, Long> {
    public ProfessorDAO(final Configuration configuration, @NotNull final Class<Professor> entity) {
        super(configuration, entity);
    }

    @SuppressWarnings("unchecked")
    public boolean isActive(final Professor professor) {
        this.open();
        try {
            final String hql = "from Turma where responsavel = :professor";
            final List<Turma> turmas = this.session.createQuery(hql)
                    .setParameter("professor", professor)
                    .getResultList();
            return turmas.stream().anyMatch(turma -> turma.getEstado() != FECHADA);
        } finally {
            this.close();
        }
    }
}
