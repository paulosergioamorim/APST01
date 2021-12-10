package datasources;

import models.entitys.Aluno;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class AlunoDAO extends DAO<Aluno, Long> {
    public AlunoDAO(Configuration configuration, @NotNull Class<Aluno> entity) {
        super(configuration, entity);
    }
}
