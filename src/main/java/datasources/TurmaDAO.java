package datasources;

import models.entitys.Turma;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class TurmaDAO extends DAO<Turma, Integer> {
    public TurmaDAO(Configuration configuration, @NotNull Class<Turma> entity) {
        super(configuration, entity);
    }
}
