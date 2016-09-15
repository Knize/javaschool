package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.knize.hyperloop.entities.CapsuleEntity;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.entities.TicketEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by knize on 08.09.16.
 */
public class WatchTicketsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int capsuleID = Integer.parseInt(req.getParameter("capsule"));
            String dateStr = req.getParameter("date");
            SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            try {
                Date date = new Date(timestampFormat.parse(dateStr).getTime());
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DATE, 1);
                java.util.Date nextDayUtil = c.getTime();
                Date nextDay = new java.sql.Date(nextDayUtil.getTime());
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query queryShedule = session.createQuery("from CapsulesScheduleEntity " +
                        "where capsuleByCapsuleId.capsuleId = :capsuleID " +
                        "and arrivalTime between :date and :nextDay")
                        .setParameter("capsuleID", capsuleID)
                        .setParameter("date", date)
                        .setParameter("nextDay", nextDay);
                List<CapsulesScheduleEntity> cseList = (List<CapsulesScheduleEntity>) queryShedule.list();
                List<TicketEntity> ticketList = Collections.emptyList();
                if (cseList.size() > 0) {
                    long tripId = cseList.get(0).getTrip_ID();
                    Query queryTickets = session.createQuery("from TicketEntity " +
                            "where capsuleByCapsuleId.capsuleId = :capsuleID " +
                            "and tripID=:tripId").
                            setParameter("capsuleID", capsuleID)
                            .setParameter("tripId", tripId);
                    ticketList = queryTickets.list();
                }
                req.setAttribute("ticketList", ticketList);
                req.setAttribute("capsuleID", capsuleID);
                session.close();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {

        }


        req.getRequestDispatcher("/WEB-INF/cms/watchTickets.jsp").forward(req, resp);
    }
}
