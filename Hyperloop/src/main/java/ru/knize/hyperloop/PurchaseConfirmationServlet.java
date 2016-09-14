package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.StationEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by knize on 13.09.16.
 */
public class PurchaseConfirmationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/purchaseConfirmation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("full_name");
        String birthdateStr = req.getParameter("birthdate");
        long tripId = 0;
        try {
            tripId = Long.parseLong(req.getParameter("trip_id"));
        } catch (NumberFormatException e) {

        }
        int children = Integer.parseInt(req.getParameter("children"));
        boolean car = Integer.parseInt(req.getParameter("car")) == 1;
        int capsuleId = Integer.parseInt(req.getParameter("capsule"));
        int scheduleId = Integer.parseInt(req.getParameter("schedule"));
        int depStationId = Integer.parseInt(req.getParameter("depStation"));
        int arrStationId = Integer.parseInt(req.getParameter("arrStation"));
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query querySchedule = session.createQuery("from CapsulesScheduleEntity " +
                "where capsuleScheduleId=:scheduleId").setParameter("scheduleId", scheduleId);
        CapsulesScheduleEntity cse = (CapsulesScheduleEntity) querySchedule.getSingleResult();
        Query queryDepStation = session.createQuery("from StationEntity where stationId=:depStationId").setParameter("depStationId", depStationId);
        Query queryArrStation = session.createQuery("from StationEntity where stationId=:arrStationId").setParameter("arrStationId", arrStationId);
        StationEntity depStation = (StationEntity) queryDepStation.getSingleResult();
        StationEntity arrStation = (StationEntity) queryArrStation.getSingleResult();


        int depStationIndex = depStation.getStationIndex();
        int arrStationIndex = arrStation.getStationIndex();
        Query queryStationsTrip = session.createQuery("from StationEntity where stationIndex between :depStationIndex and :arrStationIndex")
                .setParameter("depStationIndex", depStationIndex).setParameter("arrStationIndex", arrStationIndex);
        List<StationEntity> stationsOfTrip = queryStationsTrip.list();
        int range = 0;
        for (StationEntity station : stationsOfTrip) {
            range += station.getRangeKm();
        }

        Query queryArrSchedule = session.createQuery("from CapsulesScheduleEntity " +
                "where stationByStationId.stationIndex=:arrStationIndex " +
                "and trip_ID=:tripId")
                .setParameter("arrStationIndex",arrStationIndex)
                .setParameter("tripId", tripId);
        CapsulesScheduleEntity cseArr = (CapsulesScheduleEntity) queryArrSchedule.getSingleResult();
        double price = CapsuleMath.computePrice(range);
        System.out.println(arrStation.getStationName());
        System.out.println(depStation.getStationName());
        req.setAttribute("depStation", depStation);
        req.setAttribute("arrStation", arrStation);
        req.setAttribute("schedule", cse);
        req.setAttribute("cseArr", cseArr);
        req.setAttribute("name", name);
        req.setAttribute("children", children > 0 ? children : "No children");
        req.setAttribute("car", car ? "Yes" : "No car");
        req.setAttribute("birthdate", birthdateStr);
        req.setAttribute("price", price);
        doGet(req, resp);
    }
}
