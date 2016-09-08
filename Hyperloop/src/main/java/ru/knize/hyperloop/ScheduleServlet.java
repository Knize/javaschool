package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.knize.hyperloop.entities.CapsuleEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by knize on 07.09.16.
 */
public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryCapsules = session.createQuery("from CapsuleEntity");
        List<CapsuleEntity> capsulesList = queryCapsules.list();
        req.setAttribute("capsulesList", capsulesList);
        Query queryStations = session.createQuery("from StationEntity");
        List<CapsuleEntity> stationsList = queryStations.list();
        req.setAttribute("stationsList", stationsList);
        req.getRequestDispatcher("/WEB-INF/schedule.jsp").forward(req, resp);
        session.close();
    }
}
