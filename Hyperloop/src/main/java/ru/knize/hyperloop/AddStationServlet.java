package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import ru.knize.hyperloop.entities.StationEntity;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by knize on 02.09.16.
 */
public class AddStationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query timezoneQuery = session.createQuery("from StationEntity");
        List stationList = timezoneQuery.list();

        req.setAttribute("StationList", stationList);
        req.getRequestDispatcher("/WEB-INF/cms/addStation.jsp").forward(req, resp);
        session.close();
    }

}
