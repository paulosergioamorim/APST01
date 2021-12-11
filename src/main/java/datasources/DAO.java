package datasources;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
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
    private final SessionFactory sessionFactory;
    private final Class<T> entity;
    protected Session session;

    /**
     * @param configuration Configuration to be used
     * @param entity Entity Class
     */
    public DAO(@NotNull final Configuration configuration, @NotNull final Class<T> entity) {
        this.sessionFactory = configuration.buildSessionFactory();
        if (entity.getAnnotation(Entity.class) == null)
            throw new IllegalArgumentException("Class must be annotated with @Entity");
        this.entity = entity;
    }

    private @NotNull String getEntityName() {
        final String name = this.entity.getAnnotation(Entity.class).name();
        if (name.isEmpty())
            return this.entity.getSimpleName();
        return name;
    }

    protected void open() { this.session = this.sessionFactory.openSession(); }

    protected void close() { this.session.close(); }

    /**
     * Save entity in database
     *
     * @param t Entity to be saved
     */
    public void save(final T t) {
        this.open();
        try {
            this.session.beginTransaction();
            this.session.save(t);
            this.session.getTransaction().commit();
        } catch (final Exception e) {
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
    public void update(final T t) {
        this.open();
        try {
            this.session.beginTransaction();
            this.session.update(t);
            this.session.getTransaction().commit();
        } catch (final Exception e) {
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
    public void delete(final T t) {
        this.open();
        try {
            this.session.beginTransaction();
            this.session.delete(t);
            this.session.getTransaction().commit();
        } catch (final Exception e) {
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
    public T get(final K key) {
        this.open();
        try {
            return this.session.get(this.entity, (Serializable) key);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
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
    public boolean exists(final K key) { return this.get(key) != null; }

    /**
     * Count objects in entity
     *
     * @return Number of objects
     */
    public long count() {
        this.open();
        try {
            final String hql = "select count(*) from " + this.getEntityName();
            return (long) this.session.createQuery(hql).uniqueResult();
        } catch (final Exception e) {
            e.printStackTrace();
            return 0;
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
            final String hql = "from " + this.getEntityName();
            return this.session.createQuery(hql).getResultList();
        } catch (final Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            this.close();
        }
    }
}
