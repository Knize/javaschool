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
import java.util.List;
import java.util.Locale;

/**
 * Created by knize on 14.09.16.
 */
public class PurchaseSuccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/purchaseSuccess.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fromStationId = Integer.parseInt(req.getParameter("fromStation"));
        int toStationId = Integer.parseInt(req.getParameter("toStation"));
        int capsuleId = Integer.parseInt(req.getParameter("capsule"));
        int scheduleId = Integer.parseInt(req.getParameter("schedule"));
        String departureTimeStr = req.getParameter("departureTime");
        String arrivalTime = req.getParameter("arrivalTime");
        String name = req.getParameter("name");
        String birthdateStr = req.getParameter("birthdate");

        int children;
        if (req.getParameter("children").length() > 3) {
            children = 0;
        } else {
            children = Integer.parseInt(req.getParameter("children"));
        }


        SimpleDateFormat timestampFormat = new SimpleDateFormat("d MMMM, yyyy", Locale.US);
        Session session = HibernateUtil.getSessionFactory().openSession();
        PersonEntity passenger;
        try {
            session.beginTransaction();
            System.out.println(birthdateStr);
            Date birthdate = new java.sql.Date(timestampFormat.parse(birthdateStr).getTime());
            System.out.println(birthdate);


            Query queryPersons = session.createQuery("from PersonEntity " +
                    "where birthdate=:birthdate and name=:name")
                    .setParameter("birthdate", birthdate)
                    .setParameter("name", name);
            List<PersonEntity> passengers = queryPersons.list();

            if (passengers.size() == 0) {
                passenger = new PersonEntity();
                passenger.setBirthdate(birthdate);
                passenger.setName(name);
                session.persist(passenger);
            }
            else
                passenger = passengers.get(0);


            boolean car = req.getParameter("car").equals("Yes");
            double price = Double.parseDouble(req.getParameter("price"));

            TicketEntity ticket = new TicketEntity();
            Query queryAccount = session.createQuery("from AccountEntity where accountId=0");
            AccountEntity account = (AccountEntity) queryAccount.getSingleResult();
            Query queryCapsule = session.createQuery("from CapsuleEntity " +
                    "where capsuleId=:capsuleId")
                    .setParameter("capsuleId", capsuleId);
            CapsuleEntity capsule = (CapsuleEntity) queryCapsule.getSingleResult();
            Query querySchedule = session.createQuery("from CapsulesScheduleEntity " +
                    "where capsuleScheduleId=:scheduleId")
                    .setParameter("scheduleId", scheduleId);
            CapsulesScheduleEntity cse = (CapsulesScheduleEntity) querySchedule.getSingleResult();
            long tripId = cse.getTrip_ID();
            Query queryTicket = session.createQuery("from TicketEntity " +
                    "where tripID=:tripId " +
                    "and personByPersonId.name=:name " +
                    "and personByPersonId.birthdate =:birthdate")
                    .setParameter("tripId", tripId)
                    .setParameter("name", name)
                    .setParameter("birthdate", birthdate);
            List<TicketEntity> ticketCheck = queryTicket.list();
            if (ticketCheck.isEmpty()) {
                Query queryFromStation = session.createQuery("from StationEntity " +
                        "where stationId=:fromStationId")
                        .setParameter("fromStationId", fromStationId);
                Query queryToStation = session.createQuery("from  StationEntity " +
                        "where stationId=:toStationId")
                        .setParameter("toStationId", toStationId);
                StationEntity fromStation = (StationEntity) queryFromStation.getSingleResult();
                StationEntity toStation = (StationEntity) queryToStation.getSingleResult();
                ticket.setAccountByAccountId(account);
                ticket.setCapsuleByCapsuleId(capsule);
                ticket.setCarSlot((byte) (car ? 1 : 0));
                System.out.println("TripID" + cse.getTrip_ID());
                ticket.setTripID(cse.getTrip_ID());
                ticket.setChildren(children);
                ticket.setPersonByPersonId(passenger);
                ticket.setPrice(price);
                ticket.setStationByArrivalStationId(toStation);
                ticket.setStationByDepartureStationId(fromStation);


                session.persist(ticket);

                TrafficUtil.persistTraffic(fromStation, toStation, session, ticket);
            } else {
                req.setAttribute("block", 1);
            }
            session.getTransaction().commit();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doGet(req, resp);

    }
}
