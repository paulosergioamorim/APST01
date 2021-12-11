package datasources;

import models.MatriculaID;
import models.entitys.Matricula;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class MatriculaDAO extends DAO<Matricula, MatriculaID> {
    public MatriculaDAO(final Configuration configuration, @NotNull final Class<Matricula> entity) {
        super(configuration, entity);
    }
}
