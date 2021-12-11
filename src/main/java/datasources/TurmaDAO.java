package datasources;

import models.entitys.Turma;
import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import static models.Estado.FECHADA;

public class TurmaDAO extends DAO<Turma, Integer> {
    public TurmaDAO(Configuration configuration, @NotNull Class<Turma> entity) {
        super(configuration, entity);
    }

    /**
     * Load Turma object with your matriculas
     * @param key id of the object
     * @return loaded object
     */
    public Turma load(int key) {
        this.open();
        try {
            Turma turma = session.load(Turma.class, key);
            Hibernate.initialize(turma.getMatriculas());
            return turma;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.close();
        }
    }

    public void closeTurma(int key) {
        this.open();
        try {
            session.beginTransaction();
            String hql =
            """
            update Turma
            set estado = :estado
            where id = :id
            """;
            session.createQuery(hql)
                    .setParameter("estado", FECHADA)
                    .setParameter("id", key)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
    }
}
