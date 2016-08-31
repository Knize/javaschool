package ru.knize.hyperloop;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by knize on 31.08.16.
 */
public class FrontServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String resultPage;
        try {
            RequestHelper helper = new RequestHelper(request);
            Command command = helper.getCommand();
            resultPage = command.execute(request, response);
        }catch (Exception e){
            //Logger.getLogger(this.getClass().toString()).logException(e, Level.WARNING);
            resultPage = "/WEB-INF/test.jsp";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(resultPage);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }
}
