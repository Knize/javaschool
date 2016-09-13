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
        List<CapsuleEntity> capsuleList = query.list();
        req.setAttribute("capsuleList", capsuleList);
        try {
            int deletedCapsuleNumber = Integer.parseInt(req.getParameter("deletedCapsuleNumber"));
            session.beginTransaction();
            CapsuleEntity deletedCapsule = (CapsuleEntity) session.
                    createQuery("from CapsuleEntity where capsuleId=:deletedCapsuleNumber").
                    setParameter("deletedCapsuleNumber", deletedCapsuleNumber).getSingleResult();
            session.delete(deletedCapsule);
            session.getTransaction().commit();
            req.removeAttribute("deletedCapsuleNumber");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/cms/addCapsule.jsp").forward(req, resp);
        session.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CapsuleEntity ce = new CapsuleEntity();
        try {
            int carSlots = Integer.parseInt(req.getParameter("carSlots"));
            int seatsNumber = Integer.parseInt(req.getParameter("seatsNumber"));
            session.beginTransaction();
            ce.setCarSlots(carSlots);
            ce.setSeatsNumber(seatsNumber);
            session.persist(ce);
            session.getTransaction().commit();
            System.out.println("OK");
        } catch (NumberFormatException e) {

        }
        session.close();
        doGet(req, resp);
    }


}