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
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

/**
 * Created by knize on 05.09.16.
 */
public class AddCapsuleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from CapsuleEntity");
        List<CapsuleEntity> capsulesList = query.list();
        req.setAttribute("capsulesList", capsulesList);
        req.getRequestDispatcher("/WEB-INF/cms/addCapsule.jsp").forward(req, resp);
        session.close();
    }

    private void fillSchedule(CapsulesScheduleEntity cse, Session session) {
        StationEntity startStation = cse.getStationByStationId();
        boolean direction = cse.isDirection();
        int tripType = cse.getTripType();

        CapsuleEntity capsule = cse.getCapsuleByCapsuleId();
        Query queryStations = session.createQuery("from StationEntity");
        List<StationEntity> stations = queryStations.list();
        int startIndex = startStation.getStationIndex();
        while (startIndex < stations.size() && startIndex >= 0) {
            StationEntity nextStation = stations.get(startIndex + 1);
            int range = nextStation.getRangeKm();
            Duration tripTime = CapsuleMath.countTime(range);
            Timestamp departureTime = cse.getDepartureTime();
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
            if(direction)
                startIndex++;
            else
                startIndex--;
        }
    }
}