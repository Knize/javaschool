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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer capsuleID = null;
        try {
            capsuleID = Integer.parseInt(req.getParameter("capsule"));
        } catch (NumberFormatException | NullPointerException e) {

        }


        Query queryCapsules = session.createQuery("from TicketEntity");
        List<CapsuleEntity> capsulesList = queryCapsules.list();
        req.setAttribute("capsulesList", capsulesList);
        req.setAttribute("selectedCapsuleID", capsuleID);
        if (capsuleID != null) {
            Integer finalCapsuleID = capsuleID; // for lambda sake
            Optional<CapsuleEntity> foundCapsule = capsulesList.stream().filter(
                    (capsule) -> capsule.getCapsuleId() == finalCapsuleID).findFirst();
            if (foundCapsule.isPresent()) {
                List<TicketEntity> ticketList = foundCapsule.get()
                        .getTicketsByCapsuleId()
                        .stream().sorted((a, b) -> Integer.compare(a.getTicketId(), b.getTicketId()))
                        .collect(Collectors.toList());
                req.setAttribute("ticketList", ticketList);
                System.out.println("Hello!");
                System.out.println(ticketList);
            }
        }

        req.getRequestDispatcher("/WEB-INF/cms/watchTickets.jsp").forward(req, resp);
    }
}
