package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.knize.hyperloop.entities.CapsuleEntity;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.StationEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by knize on 03.09.16.
 */
public class WatchCapsulesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CapsuleEntity selectedCapsule = null;
        List<CapsulesScheduleEntity> scheduleList = null;


        try {
            int capsuleID = Integer.parseInt(req.getParameter("capsule"));
            Query querySchedule = session.createQuery("from CapsulesScheduleEntity where capsuleByCapsuleId.capsuleId =:capsuleID").setParameter("capsuleID", capsuleID);
            Query queryCapsule = session.createQuery("from CapsuleEntity where capsuleId =:capsuleID").setParameter("capsuleID", capsuleID);
            scheduleList = querySchedule.list();
            selectedCapsule = (CapsuleEntity) queryCapsule.getSingleResult();
        } catch (NumberFormatException e) {
            scheduleList = new ArrayList<CapsulesScheduleEntity>();
            //session.close();
        }

        Query queryStations = session.createQuery("from StationEntity ");
        List<StationEntity> stationList = queryStations.list();
        Query queryCapsules = session.createQuery("from CapsuleEntity ");
        List<CapsuleEntity> capsulesList = queryCapsules.list();
        req.setAttribute("stationList", stationList);
        req.setAttribute("scheduleList", scheduleList);
        req.setAttribute("selectedCapsule", selectedCapsule);
        req.setAttribute("capsulesList", capsulesList);
        req.getRequestDispatcher("/WEB-INF/cms/watchCapsules.jsp").forward(req, resp);
        session.close();
    }

}
