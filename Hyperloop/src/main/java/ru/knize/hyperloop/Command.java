package ru.knize.hyperloop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.Predicate;

/**
 * Created by knize on 31.08.16.
 */
public interface Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
