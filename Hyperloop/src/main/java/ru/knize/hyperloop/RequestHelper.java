package ru.knize.hyperloop;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by knize on 31.08.16.
 */
public class RequestHelper {
    HttpServletRequest request;
    String path;
    String fileName;
    String host;

    public RequestHelper(HttpServletRequest request){
       this.request = request;
        path = request.getPathInfo();
        fileName = request.getLocalName();
        host = request.getServerName();

    }

    public Command getCommand(){
        if(path.startsWith("/admin")){
            Command command = CommandFactory.getInstance().getCMS(request);
        }
        return null;
    }
}
