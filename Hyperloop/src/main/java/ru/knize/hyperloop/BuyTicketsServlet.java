package ru.knize.hyperloop;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by knize on 09.09.16.
 */
public class BuyTicketsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/buyTickets.jsp").forward(req, resp);
    }
/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fromStation = Integer.parseInt(req.getParameter("from_station"));
        int toStation = Integer.parseInt(req.getParameter("to_station"));
        String startDateStr = req.getParameter("departure_time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //surround below line with try catch block as below code throws checked exception
        Timestamp departureTime = null;
        try {
            Date startDate = sdf.parse(startDateStr);
            long time = startDate.getTime();
            //departureTime = new Timestamp(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //do further processing with Date object
*/
}

