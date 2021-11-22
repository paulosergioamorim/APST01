package cgd.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * @author Paulo Sergio
 * @param <T> Type of the entity
 * @param <K> Type of the primary key
 */
public abstract class DAO<T, K> {
    protected Session session;

    public DAO(String url) {
        Configuration configuration = new Configuration().configure(url);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    /**
     * Commit changes on database
     */
    protected void commit() { session.beginTransaction().commit(); }

    /**
     * Save object on database
     * @param t object to be saved
     */
    public abstract void save(T t);

    /**
     * Update object on database
     * @param t object to be updated
     */
    public abstract void update(T t);

    /**
     * Delete object from database
     * @param t object to be deleted
     */
    public abstract void delete(T t);

    /**
     * Find object by key on database
     * @param k key of object to be found
     * @return object found
     */
    public abstract T find(K k);

    /**
     * Exists object by key on database
     * @param k key of object to be found
     * @return true if object exists, false otherwise
     */
    public abstract boolean exists(K k);

    /**
     * Count objects from entity on database
     * @return number of objects
     */
    public abstract long count();

    /**
     * List objects from entity on database
     * @return list of objects
     */
    public abstract List<T> findAll();
}
