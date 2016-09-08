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
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by knize on 02.09.16.
 */
public class AddStationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/cms/addStation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        StationEntity se = new StationEntity();
        req.setCharacterEncoding("UTF-8");
        se.setStationIndex(Integer.parseInt(req.getParameter("station_index")));
        se.setStationName(req.getParameter("station_name"));
        se.setTimezone(req.getParameter("timezone"));
        se.setRangeKm(Integer.parseInt(req.getParameter("range_km")));

        session.persist(se);
        session.getTransaction().commit();

        doGet(req, resp);
        session.close();
    }
}
