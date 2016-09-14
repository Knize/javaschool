package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.knize.hyperloop.entities.CapsuleEntity;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.entities.TicketEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by knize on 09.09.16.
 */
public class PurchaseTicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int capsuleId = Integer.parseInt(req.getParameter("capsule"));
        int scheduleId = Integer.parseInt(req.getParameter("scheduleEntry"));
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryCapsule = session.createQuery("from CapsuleEntity where capsuleId=:capsuleId").setParameter("capsuleId", capsuleId);
        Query queryShedule = session.createQuery("from CapsulesScheduleEntity where capsuleScheduleId=:scheduleId").setParameter("scheduleId", scheduleId);
        Query queryStations = session.createQuery("from StationEntity");
        CapsuleEntity capsule = (CapsuleEntity) queryCapsule.getSingleResult();
        CapsulesScheduleEntity scheduleEntry = (CapsulesScheduleEntity) queryShedule.getSingleResult();
        List<StationEntity> stationList = queryStations.list();
        req.setAttribute("stationList", stationList);
        req.setAttribute("scheduleEntry", scheduleEntry);
        req.setAttribute("capsule", capsule);
        req.getRequestDispatcher("/WEB-INF/purchaseTicket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(req.getParameter("scheduleEntry"));
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query querySchedule = session.createQuery("from CapsulesScheduleEntity where capsuleScheduleId=:scheduleId").setParameter("scheduleId", scheduleId);
        CapsulesScheduleEntity cse = (CapsulesScheduleEntity) querySchedule.getSingleResult();
        req.setAttribute("scheduleEntry", cse);
        int capsuleId = cse.getCapsuleByCapsuleId().getCapsuleId();
        Query queryCapsule = session.createQuery("from CapsuleEntity where capsuleId=:capsuleId").setParameter("capsuleId", capsuleId);
        CapsuleEntity capsule = (CapsuleEntity) queryCapsule.getSingleResult();
        int seatsNumber = capsule.getSeatsNumber();

        StationEntity fromStation = cse.getStationByStationId();

        try {
            int toStationIndex = Integer.parseInt(req.getParameter("arrStation"));
            Query queryToStation = session.createQuery("from StationEntity " +
                    "where stationId=:toStationIndex")
                    .setParameter("toStationIndex", toStationIndex);
            StationEntity toStation = (StationEntity) queryToStation.getSingleResult();
            if (!TrafficUtil.canGo(fromStation, toStation, cse)) {
                req.setAttribute("block", 1);
            }
        } catch (NumberFormatException e) {

        }

        long tripID = Long.parseLong(req.getParameter("trip_id"));
        req.setAttribute("tripID", tripID);


        session.close();
        doGet(req, resp);
    }
}
