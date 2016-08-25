package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import ru.knize.hyperloop.entities.PassengerEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by knize on 25.08.16.
 */
public class TestServlet extends HttpServlet {

    ServiceRegistry serviceRegistry;
    SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from PassengerEntity");
        List<PassengerEntity> passengerEntityList = query.list();
        req.setAttribute("passengerEntityList", passengerEntityList);
        req.getRequestDispatcher("/test.jsp").forward(req, resp);
        session.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        PassengerEntity pe = new PassengerEntity();
        pe.setEmail(req.getParameter("email"));
        pe.setName(req.getParameter("name"));

        session.persist(pe);
        session.getTransaction().commit();

        doGet(req, resp);
        session.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        Configuration configuration = new Configuration().configure("config.xml");
        configuration.addAnnotatedClass(PassengerEntity.class);
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);


    }

    @Override
    public void destroy() {
        super.destroy();
        sessionFactory.close();
    }
}
