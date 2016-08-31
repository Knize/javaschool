package ru.knize.hyperloop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by knize on 31.08.16.
 */
public class CommandFactoryImpl extends CommandFactory{
    @Override
    Command getCMS(HttpServletRequest request) {
        return new Command() {
            @Override
            public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
                return "/WEB-INF/cms/cmsIndex.jsp";
            }

        };
    }
}
