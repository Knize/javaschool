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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by knize on 09.09.16.
 */
public class BuyTicketsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryStations = session.createQuery("from StationEntity ");
        List<StationEntity> stationList = queryStations.list();
        SimpleDateFormat timestampFormat = new SimpleDateFormat("d MMMM, yyyy", Locale.US);
        req.setAttribute("cseList", Collections.emptyList());
        try {
            int fromStationId = Integer.parseInt(req.getParameter("from_station"));
            int toStationId = Integer.parseInt(req.getParameter("to_station"));
            String fromDateStr = req.getParameter("from_time");
            String toDateStr = req.getParameter("to_time");
            req.setAttribute("fromStationId", fromStationId);
            req.setAttribute("toStationId", toStationId);
            req.setAttribute("fromDate", fromDateStr);
            req.setAttribute("toDate", toDateStr);
            Query queryFromStation = session.createQuery("from StationEntity where stationId=:fromStationId").setParameter("fromStationId", fromStationId);
            Query queryToStation = session.createQuery("from StationEntity where stationId=:toStationId").setParameter("toStationId", toStationId);
            StationEntity fromStation = (StationEntity) queryFromStation.getSingleResult();
            StationEntity toStation = (StationEntity) queryToStation.getSingleResult();
            req.setAttribute("fromStationName", fromStation.getStationName());
            req.setAttribute("toStationName", toStation.getStationName());

            Date fromDate = timestampFormat.parse(fromDateStr);
            Date toDate = timestampFormat.parse(toDateStr);

            long time = fromDate.getTime();
            Timestamp fromTimestamp = new Timestamp(time);
            time = toDate.getTime();
            Timestamp toTimestamp = new Timestamp(time);
            System.out.println(fromTimestamp);
            System.out.println(toTimestamp);
            Query queryCapsulesSchedule = session.createQuery("from CapsulesScheduleEntity " +
                    "where departureTime >:fromTimestamp " +
                    "AND arrivalTime <:toTimestamp ")
                    .setParameter("toTimestamp", toTimestamp)
                    .setParameter("fromTimestamp", fromTimestamp);
            List<CapsulesScheduleEntity> cseList = queryCapsulesSchedule.list();
            for (CapsulesScheduleEntity cse: cseList){
                cse.getStationByStationId().getStationName();
            }
            long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

            Calendar date = Calendar.getInstance();
            long t = date.getTimeInMillis();
            Date currentDatePlus = new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
            Timestamp currentDatePlusTimestamp = new Timestamp(currentDatePlus.getTime());

            cseList = cseList.stream()
                    .filter(s -> s.getArrivalTime().after(currentDatePlusTimestamp))
                    .collect(Collectors.toList());
            req.setAttribute("cseList", cseList);

        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }

        req.setAttribute("stationList", stationList);
        req.getRequestDispatcher("/WEB-INF/buyTickets.jsp").forward(req, resp);


    }
}

