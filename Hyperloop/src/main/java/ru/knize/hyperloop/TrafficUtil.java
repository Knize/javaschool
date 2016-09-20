package ru.knize.hyperloop;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.knize.hyperloop.entities.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by knize on 14.09.16.
 */

/**
 * This class dedicated to computing, getting and saving
 * information about number of passengers in capsule by stages of the trip.
 */
public class TrafficUtil {

    /**
     * @param cse First schedule entry of the trip
     * @return List of Integers with number of tickets for each stage of trip.
     */
    public static List<Integer> getTrafficList(CapsulesScheduleEntity cse) {
        List<Integer> trafficList = Collections.emptyList();

        long tripID = cse.getTrip_ID();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query queryStationsByBranch = session.createQuery("from StationEntity ");
        List<StationEntity> branch = queryStationsByBranch.list();
        branch.sort((StationEntity a, StationEntity b) ->
                Integer.compare(b.getStationIndex(), a.getStationIndex()));
        int branchLength = branch.size() - 1;
        for (int i = 0; i < branchLength; i++) {
            int fromStationIndex = i + 1;
            int toStationIndex = i + 2;
            Query queryTraffic = session.createQuery("from TrafficEntity " +
                    "where fromStation.stationIndex=:fromStationIndex " +
                    "and toStation.stationIndex=:toStationIndex " +
                    "and tripID=:tripID")
                    .setParameter("fromStationIndex", fromStationIndex)
                    .setParameter("toStationIndex", toStationIndex)
                    .setParameter("tripID", tripID);
            List<TicketEntity> stageTickets = queryTraffic.list();
            trafficList.add(stageTickets.size());
        }
        return trafficList;
    }

    /**
     * @param from departure station
     * @param to   arrival station
     * @param cse  First schedule entry of the trip
     * @return true if there is free places in capsule
     */
    public static boolean canGo(StationEntity from, StationEntity to, CapsulesScheduleEntity cse) {
        boolean can = true;
        int fromIndex = from.getStationIndex();
        int toIndex = to.getStationIndex();
        int seatsNumber = cse.getCapsuleByCapsuleId().getSeatsNumber();
        List<Integer> trafficList = getTrafficList(cse);
        for (int i = fromIndex - 1; i < toIndex - 1; i++) {
            if (trafficList.get(i) >= seatsNumber) {
                can = false;
            }
        }
        return can;
    }

    /**
     * This method saves data about all stages where this passenger will be inside capsule in Traffic table.
     *
     * @param from    departure station
     * @param to      arrival station
     * @param session session instance (function will write to DB). There is no transaction inside.
     * @param ticket  Bought ticket
     */
    public static void persistTraffic(StationEntity from, StationEntity to, Session session, TicketEntity ticket) {
        int fromIndex = from.getStationIndex();
        int toIndex = to.getStationIndex();
        Query queryStationsTrip = session.createQuery("from StationEntity " +
                "where stationIndex between :fromIndex and :toIndex")
                .setParameter("fromIndex", fromIndex)
                .setParameter("toIndex", toIndex);

        List<StationEntity> stationsOfTrip = queryStationsTrip.list();
        stationsOfTrip.sort((StationEntity a, StationEntity b) -> Integer.compare(a.getStationIndex(), b.getStationIndex()));

        for (int i = 0; i < stationsOfTrip.size() - 1; i++) {
            TrafficEntity te = new TrafficEntity();
            te.setTripID(ticket.getTripID());
            te.setTicket(ticket);
            te.setFromStation(stationsOfTrip.get(i));
            te.setToStation(stationsOfTrip.get(i + 1));
            session.persist(te);
        }
    }
}
