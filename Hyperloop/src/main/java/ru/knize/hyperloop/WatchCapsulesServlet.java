package ru.knize.hyperloop;

import javafx.scene.input.DataFormat;
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
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int capsuleId = Integer.parseInt(req.getParameter("capsuleIdHidden"));
        int stationId = Integer.parseInt(req.getParameter("station"));
        Query queryCapsules = session.createQuery("from CapsuleEntity where capsuleId=:capsuleId").setParameter("capsuleId", capsuleId);
        CapsuleEntity capsule = (CapsuleEntity)queryCapsules.getSingleResult();
        Query queryStations = session.createQuery("from StationEntity where stationId=:stationId").setParameter("stationId", stationId);
        StationEntity station = (StationEntity) queryStations.getSingleResult();
        int direction = Integer.parseInt(req.getParameter("direction"));
        String dateStr = req.getParameter("date");
        String timeStr = req.getParameter("time");
        String timestampStr = dateStr + " " + timeStr;
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date timestampDate = timestampFormat.parse(timestampStr);

            long time = timestampDate.getTime();
            Timestamp timestampArrival = new Timestamp(time);
            Timestamp timestampDeparture = new Timestamp(timestampArrival.getTime() + + 300 * 1000L);
            CapsulesScheduleEntity cse = new CapsulesScheduleEntity();
            cse.setDirection(direction == 1);
            cse.setTripType(1);
            cse.setCapsuleByCapsuleId(capsule);
            cse.setStationByStationId(station);
            cse.setArrivalTime(timestampArrival);
            cse.setDepartureTime(timestampDeparture);
            session.beginTransaction();
            session.persist(cse);
            fillSchedule(cse, session);
            session.getTransaction().commit();


        } catch (ParseException e) {
            e.printStackTrace();
        }
        doGet(req, resp);

    }

    private void fillSchedule(CapsulesScheduleEntity cse, Session session) {
        StationEntity startStation = cse.getStationByStationId();
        boolean direction = cse.isDirection();
        int tripType = cse.getTripType();

        CapsuleEntity capsule = cse.getCapsuleByCapsuleId();
        System.out.println("capsule: " + capsule.getCapsuleId());
        Query queryStations = session.createQuery("from StationEntity");
        List<StationEntity> stations = queryStations.list();
        int startIndex = startStation.getStationIndex();
        while (startIndex < (stations.size() - 1) && startIndex >= 0) {
            StationEntity nextStation = stations.get(startIndex + 1);
            int range = nextStation.getRangeKm();
            System.out.println(range);
            Duration tripTime = CapsuleMath.countTime(range);
            System.out.println(tripTime.getSeconds());
            Timestamp departureTime = cse.getDepartureTime();
            System.out.println(departureTime);
            Timestamp nextStationArrivalTime = new Timestamp(departureTime.getTime() + (tripTime.getSeconds() * 1000L));
            Timestamp nextStationDepartureTime = new Timestamp(nextStationArrivalTime.getTime() + 300 * 1000L);
            CapsulesScheduleEntity newCse = new CapsulesScheduleEntity();
            newCse.setCapsuleByCapsuleId(capsule);
            newCse.setStationByStationId(nextStation);
            newCse.setTripType(tripType);
            newCse.setDirection(direction);
            newCse.setArrivalTime(nextStationArrivalTime);
            newCse.setDepartureTime(nextStationDepartureTime);
            session.persist(newCse);
            if (direction)
                startIndex++;
            else
                startIndex--;
        }
    }
}
