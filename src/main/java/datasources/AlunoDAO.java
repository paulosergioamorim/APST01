package datasources;

import models.entitys.Aluno;
import models.entitys.Turma;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AlunoDAO extends DAO<Aluno, Long> {
    public AlunoDAO(Configuration configuration, @NotNull Class<Aluno> entity) {
        super(configuration, entity);
    }

    @SuppressWarnings("unchecked")
    public boolean containsMatriculas(Aluno aluno) {
        this.open();
        try {
            String hql = "from Matricula where aluno = :aluno";
            List<Turma> turmas = session.createQuery(hql)
                    .setParameter("aluno", aluno)
                    .getResultList();
            return !turmas.isEmpty();
        } finally {
            this.close();
        }
    }
}
