package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.knize.hyperloop.entities.CapsuleEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by knize on 09.09.16.
 */
public class PurchaseTicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int capsuleId = Integer.parseInt(req.getParameter("capsule"));
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryCapsule = session.createQuery("from CapsuleEntity where capsuleId=:capsuleId").setParameter("capsuleId", capsuleId);
        CapsuleEntity capsule = (CapsuleEntity) queryCapsule.getSingleResult();
        req.setAttribute("capsule", capsule);
        req.getRequestDispatcher("/WEB-INF/purchaseTicket.jsp").forward(req, resp);
    }
}
