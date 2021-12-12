package datasources;

import models.MatriculaID;
import models.entitys.Matricula;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

public class MatriculaDAO extends DAO<Matricula, MatriculaID> {
    public MatriculaDAO(Configuration configuration, @NotNull Class<Matricula> entity) {
        super(configuration, entity);
    }
}
