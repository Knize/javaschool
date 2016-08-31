package ru.knize.hyperloop;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by knize on 31.08.16.
 */
abstract public class CommandFactory {

    private static CommandFactory instance;
    static CommandFactory getInstance(){
        if(instance == null)
            instance = new CommandFactoryImpl();
        return instance;
    }

    abstract Command getCMS(HttpServletRequest request);
}
