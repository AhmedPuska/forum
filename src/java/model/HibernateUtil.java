package model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    
    

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory createSessionFactory() {
        Configuration config = new Configuration();
        config.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        sessionFactory = config.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static void close() {
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

    public static void main(String[] args) {
        SessionFactory sf = createSessionFactory();
        System.out.println(sf);
        SessionFactory sf1 = createSessionFactory();
        System.out.println(sf1);
        SessionFactory sf2 = createSessionFactory();
        System.out.println(sf2);
        System.exit(0);

    }
}
