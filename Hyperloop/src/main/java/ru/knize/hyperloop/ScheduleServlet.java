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
import java.util.*;
import java.sql.Timestamp;
import java.util.stream.Collectors;

/**
 * Created by knize on 07.09.16.
 */
public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer stationID = null;
        try {
            stationID = Integer.parseInt(req.getParameter("station"));
        } catch (NumberFormatException | NullPointerException e) {
        }

        Query queryStations = session.createQuery("from StationEntity");
        List<StationEntity> stationsList = queryStations.list();
        req.setAttribute("stationsList", stationsList);
        req.setAttribute("selectedStationID", stationID);

        List<CapsulesScheduleEntity> scheduleList = new ArrayList<CapsulesScheduleEntity>();
        if (stationID != null) {
            Integer finalStationID = stationID; // for lambda sake
            Optional<StationEntity> foundStation = stationsList.stream().filter((station) -> station.getStationId() == finalStationID).findFirst();
            if (foundStation.isPresent()) {
                scheduleList = foundStation.get()
                        .getCapsulesSchedulesByStationId()
                        .stream().sorted((a, b) -> a.getArrivalTime().compareTo(b.getArrivalTime()))
                        .collect(Collectors.toList());

                long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

                Calendar date = Calendar.getInstance();
                long t = date.getTimeInMillis();
                Date currentDatePlus = new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
                Timestamp currentDatePlusTimestamp = new Timestamp(currentDatePlus.getTime());

                scheduleList = scheduleList.stream()
                        .filter(s -> s.getArrivalTime().after(currentDatePlusTimestamp))
                        .collect(Collectors.toList());
                req.setAttribute("scheduleList", scheduleList);
            }
        }
        req.getRequestDispatcher("/WEB-INF/schedule.jsp").forward(req, resp);
        session.close();
    }


}
