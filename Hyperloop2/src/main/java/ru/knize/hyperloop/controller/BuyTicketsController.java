package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knize.hyperloop.services.StationService;

/**
 * Created by knize on 05.10.16.
 */
@Controller
public class BuyTicketsController {
    @Autowired
    StationService stationService;

    @GetMapping("/buyTickets")
    public String init(
            @RequestParam(value = "from_station", defaultValue = "0") String fromStationStr,
            @RequestParam(value = "to_station", defaultValue = "0") String toStationStr,
            Model model){

        model.addAttribute("toStationId", Integer.parseInt(toStationStr));
        model.addAttribute("fromStationId", Integer.parseInt(fromStationStr));
        model.addAttribute("stationList", stationService.getStations());

        return "buyTickets";
    }
}
