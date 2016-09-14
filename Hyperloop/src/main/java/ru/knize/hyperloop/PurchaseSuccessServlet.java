package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.knize.hyperloop.entities.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 * Created by knize on 14.09.16.
 */
public class PurchaseSuccessServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fromStationId = Integer.parseInt(req.getParameter("fromStation"));
        int toStationId = Integer.parseInt(req.getParameter("toStation"));
        int capsuleId = Integer.parseInt(req.getParameter("toStation"));
        int scheduleId = Integer.parseInt(req.getParameter("schedule"));
        String departureTimeStr = req.getParameter("departureTime");
        String arrivalTime = req.getParameter("arrivalTime");
        String name = req.getParameter("name");
        String birthdateStr = req.getParameter("birthdate");
        int children = Integer.parseInt(req.getParameter("children"));
        SimpleDateFormat timestampFormat = new SimpleDateFormat("d M, yyyy");
        Session session = HibernateUtil.getSessionFactory().openSession();
        PersonEntity passenger;
        try {
            session.beginTransaction();
            Date birthdate = new java.sql.Date(timestampFormat.parse(birthdateStr).getTime());
            passenger = new PersonEntity();
            passenger.setBirthdate(birthdate);
            passenger.setName(name);
            session.persist(passenger);
            System.out.println("Car: " + req.getParameter("car"));
            boolean car = Integer.parseInt(req.getParameter("car")) == 1;
            double price = Double.parseDouble(req.getParameter("price"));

            TicketEntity ticket = new TicketEntity();
            Query queryAccount = session.createQuery("from AccountEntity where accountId=0");
            AccountEntity account = (AccountEntity) queryAccount.getSingleResult();
            Query queryCapsule = session.createQuery("from CapsuleEntity where capsuleId=:capsuleId").setParameter("capsuleId", capsuleId);
            CapsuleEntity capsule = (CapsuleEntity) queryCapsule.getSingleResult();
            Query querySchedule = session.createQuery("from CapsulesScheduleEntity where capsuleScheduleId=:scheduleId").setParameter("scheduleId", scheduleId);
            CapsulesScheduleEntity cse = (CapsulesScheduleEntity) querySchedule.getSingleResult();
            ticket.setAccountByAccountId(account);
            ticket.setCapsuleByCapsuleId(capsule);
            ticket.setCarSlot((byte) (car ? 1 : 0));
            ticket.setTripID(cse.getTrip_ID());
            ticket.setChildren(children);
            ticket.setPersonByPersonId(passenger);
            ticket.setPrice(price);
            session.persist(ticket);
            Query queryFromStation = session.createQuery("from StationEntity where stationId=:fromStationId").setParameter("fromStationId", fromStationId);
            Query queryToStation = session.createQuery("from StationEntity where stationId=:toStationId").setParameter("toStationId", toStationId);
            StationEntity fromStation = (StationEntity) queryFromStation.getSingleResult();
            StationEntity toStation = (StationEntity) queryToStation.getSingleResult();
            TrafficUtil.persistTraffic(fromStation, toStation, session, ticket);
        } catch (ParseException e) {

        } finally {
            session.getTransaction().commit();
        }


    }
}
