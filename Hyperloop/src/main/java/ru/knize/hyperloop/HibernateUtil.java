package ru.knize.hyperloop;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by knize on 03.09.16.
 */
public class HibernateUtil {
    private static HibernateUtil ourInstance = new HibernateUtil();

    public static ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    public static HibernateUtil getInstance() {
        return ourInstance;
    }
    public void init(){
        try {
            Configuration configuration = new Configuration().configure("config.xml");
            serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void destroy(){
        sessionFactory.close();
    }

    private HibernateUtil() {

    }
}
