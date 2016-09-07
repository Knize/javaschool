package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.knize.hyperloop.entities.CapsuleEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by knize on 03.09.16.
 */
public class WatchCapsulesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from CapsuleEntity");
        List<CapsuleEntity> capsulesList = query.list();

        req.setAttribute("capsulesList", capsulesList);
        getServletContext().getRequestDispatcher("/WEB-INF/cms/watchCapsules.jsp").forward(req, resp);
        session.close();
    }


}
