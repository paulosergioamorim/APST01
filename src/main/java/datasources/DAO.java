package datasources;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO Hibernate Class
 *
 * @param <T> Entity Class
 * @param <K> Primary Key (don't accept primitives)
 * @author Paulo Sergio
 * @version 1.0
 */
public abstract class DAO<T, K> {
    private final SessionFactory factory;
    private final Class<T> entity;
    protected Session session;

    /**
     * @param cfg    Configuration to be used
     * @param entity Entity Class
     */
    public DAO(@NotNull Configuration cfg, @NotNull Class<T> entity) {
        factory = cfg.buildSessionFactory();
        if (entity.getAnnotation(Entity.class) == null)
            throw new IllegalArgumentException("Class must be annotated with @Entity");
        this.entity = entity;
    }

    private @NotNull String getEntityName() {
        String name = entity.getAnnotation(Entity.class).name();
        if (name.isEmpty())
            return entity.getSimpleName();
        return name;
    }

    protected void open() { session = factory.openSession(); }

    protected void close() { session.close(); }

    /**
     * Save entity in database
     *
     * @param t Entity to be saved
     */
    public void save(T t) {
        this.open();
        try {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
    }

    /**
     * Update t in database
     *
     * @param t Entity to be updated
     */
    public void update(T t) {
        this.open();
        try {
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
    }

    /**
     * Delete t from database
     *
     * @param t Entity to be deleted
     */
    public void delete(T t) {
        this.open();
        try {
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
    }

    /**
     * Find object by key
     *
     * @param key Key to find entity
     * @return Entity found
     */
    public T get(K key) {
        this.open();
        try {
            return session.get(entity, (Serializable) key);
        } finally {
            this.close();
        }
    }

    /**
     * Check if object exists in database
     *
     * @param key Key to find entity
     * @return True if entity exists, false otherwise
     */
    public boolean exists(K key) { return this.get(key) != null; }

    /**
     * Count objects in entity
     *
     * @return Number of objects
     */
    public long count() {
        this.open();
        try {
            String hql = "select count(*) from " + this.getEntityName();
            return (long) session.createQuery(hql).uniqueResult();
        } finally {
            this.close();
        }
    }

    /**
     * List all objects in entity
     *
     * @return List of objects
     */
    @SuppressWarnings("unchecked")
    public List<T> toList() {
        this.open();
        try {
            String hql = "from " + this.getEntityName();
            return session.createQuery(hql).getResultList();
        } finally {
            this.close();
        }
    }
}
