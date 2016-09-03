package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.entities.StationsGraphEntity;
import ru.knize.hyperloop.entities.TimezoneEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by knize on 02.09.16.
 */
public class AddStationServlet extends HttpServlet {
    ServiceRegistry serviceRegistry;
    SessionFactory sessionFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = sessionFactory.openSession();
        Query timezoneQuery = session.createQuery("from TimezoneEntity");
        List TimezonesList = timezoneQuery.list();
        req.setAttribute("TimezonesList", TimezonesList);
        req.getRequestDispatcher("/WEB-INF/cms/addStation.jsp").forward(req, resp);
        session.close();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        Configuration configuration = new Configuration().configure("config.xml");
        serviceRegistry = configuration.getStandardServiceRegistryBuilder().build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void destroy() {
        super.destroy();
        sessionFactory.close();
    }
}
