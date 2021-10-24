package cgd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Service {
    private final Session session;

    public Service(String url) {
        Configuration cfg = new Configuration().configure(url);
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public <E> void save(E object) {
        session.save(object);
        session.beginTransaction().commit();
    }

    public <E> void delete(E object) {
        session.delete(object);
        session.beginTransaction().commit();
    }
}
