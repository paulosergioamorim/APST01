package cdp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

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

    public static void main(String[] args) {
        var service = new Service("hibernate.cfg.xml");
        service.save(new Professor(
                "Elenilson", LocalDate.of(1959,12,25), 1521566546, Titulo.Doutor
        ));
    }
}
