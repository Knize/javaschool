package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knize.hyperloop.services.StationService;
import ru.knize.hyperloop.util.TrafficUtil;

/**
 * Created by knize on 05.10.16.
 */
@Controller
public class BuyTicketsController {

    @Autowired
    StationService stationService;
    @Autowired
    TrafficUtil trafficUtil;

    @GetMapping("/buyTickets")
    public String init(
            @RequestParam(value = "from_station", defaultValue = "0") String fromStationStr,
            @RequestParam(value = "to_station", defaultValue = "0") String toStationStr,
            @RequestParam(value = "from_time", defaultValue = "0") String fromTime,
            @RequestParam(value = "to_time", defaultValue = "0") String toTime,
            @RequestParam(value = "number", defaultValue = "0") String number,
            Model model) {

        model.addAttribute("toStationId", Integer.parseInt(toStationStr));
        model.addAttribute("fromStationId", Integer.parseInt(fromStationStr));
        model.addAttribute("from_time", fromTime);
        model.addAttribute("to_time", toTime);
        model.addAttribute("number", number);
        model.addAttribute("stationList", stationService.getStations());
        if(!"0".equals(toTime) && !"0".equals(fromTime))
            model.addAttribute("routesList", trafficUtil.processRequest(number, fromStationStr, toStationStr, fromTime, toTime));
        return "buyTickets";
    }
}
