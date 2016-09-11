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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by knize on 08.09.16.
 */
public class WatchTicketsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int capsuleID = Integer.parseInt(req.getParameter("capsule"));
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from TicketEntity where capsuleByCapsuleId.capsuleId = :capsuleID").
                    setParameter("capsuleID", capsuleID);
            req.setAttribute("ticketList", query.list());
            req.setAttribute("capsuleID", capsuleID);
            session.close();
        } catch (NumberFormatException e) {

        }


        req.getRequestDispatcher("/WEB-INF/cms/watchTickets.jsp").forward(req, resp);
    }
}
