package datasources;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Generic DAO Hibernate Class
 *
 * @param <T> Entity Class
 * @param <K> Primary Key (don't accept primitives)
 * @author Paulo Sergio
 * @version 1.0
 */
public abstract class DAO<T, K> {
    private final SessionFactory sessionFactory;
    private final Class<T> entity;
    protected Session session;

    /**
     * @param configuration Configuration to be used
     * @param entity Entity Class
     */
    public DAO(@NotNull Configuration configuration, @NotNull Class<T> entity) {
        sessionFactory = configuration.buildSessionFactory();
        if (entity.getAnnotation(Entity.class) == null)
            throw new IllegalArgumentException("Class must be annotated with @Entity");
        this.entity = entity;
    }

    private @NotNull String getEntityName() {
        String name = entity.getAnnotation(Entity.class).name();
        if (name.isEmpty()) return entity.getSimpleName();
        return name;
    }

    protected void open() { session = sessionFactory.openSession(); }

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
     * Find entity by key
     *
     * @param key Key to find entity
     * @return Entity found
     */
    public T get(K key) {
        this.open();
        try {
            return session.get(entity, (Serializable) key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.close();
        }
    }

    /**
     * Check if entity exists in database
     *
     * @param key Key to find entity
     * @return True if entity exists, false otherwise
     */
    public boolean exists(K key) { return this.get(key) != null; }

    /**
     * Count entities in database
     *
     * @return Number of entities
     */
    public long count() {
        this.open();
        try {
            String sql = "select count(*) from " + this.getEntityName();
            return (long) session.createQuery(sql).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.close();
        }
    }

    /**
     * List all entities in database
     *
     * @return List of entities
     */
    @SuppressWarnings("unchecked")
    public List<T> toList() {
        this.open();
        try {
            String sql = "from " + this.getEntityName();
            return session.createQuery(sql).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            this.close();
        }
    }
}
