package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.query.Query;
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
import java.util.Date;
import java.util.List;


/**
 * Created by knize on 09.09.16.
 */
public class BuyTicketsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryStations = session.createQuery("from StationEntity ");
        List<StationEntity> stationList = queryStations.list();
        SimpleDateFormat timestampFormat = new SimpleDateFormat("d M, yyyy");
        try {
            int fromStationId = Integer.parseInt(req.getParameter("from_station"));
            int toStationId = Integer.parseInt(req.getParameter("to_station"));
            String depDateStr = req.getParameter("departure_time");
            String arrDateStr = req.getParameter("arrival_time");
            System.out.println(arrDateStr);
            req.setAttribute("fromStationId", fromStationId);
            req.setAttribute("toStationId", toStationId);
            req.setAttribute("depDate", depDateStr);
            req.setAttribute("arrDate", arrDateStr);
            Query queryFromStation = session.createQuery("from StationEntity where stationId=:fromStationId").setParameter("fromStationId", fromStationId);
            Query queryToStation = session.createQuery("from StationEntity where stationId=:toStationId").setParameter("toStationId", toStationId);
            StationEntity fromStation = (StationEntity) queryFromStation.getSingleResult();
            StationEntity toStation = (StationEntity) queryToStation.getSingleResult();
            req.setAttribute("fromStationName", fromStation.getStationName());
            req.setAttribute("toStationName", toStation.getStationName());

            Date arrDate = timestampFormat.parse(arrDateStr);
            Date depDate = timestampFormat.parse(depDateStr);
            long time = arrDate.getTime();
            Timestamp arrTimestamp = new Timestamp(time);
            time = depDate.getTime();
            Timestamp depTimestamp = new Timestamp(time);
            Query queryCapsulesSchedule = session.createQuery("from CapsulesScheduleEntity " +
                    "where departureTime <:depTimestamp AND arrivalTime >:arrTimestamp ").setParameter("depTimestamp", depTimestamp).setParameter("arrTimestamp", arrTimestamp);


        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }

        req.setAttribute("stationList", stationList);
        req.getRequestDispatcher("/WEB-INF/buyTickets.jsp").forward(req, resp);


    }
}

