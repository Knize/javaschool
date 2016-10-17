package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knize.hyperloop.entities.CapsuleEntity;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.services.CapsuleScheduleService;
import ru.knize.hyperloop.services.CapsuleService;
import ru.knize.hyperloop.services.StationService;

/**
 * Created by knize on 16.10.16.
 */
@Controller
public class PurchaseTicketController {
    @Autowired
    CapsuleScheduleService capsuleScheduleService;
    @Autowired
    CapsuleService capsuleService;
    @Autowired
    StationService stationService;

    @PostMapping("/purchaseTicket")
    public String init(@RequestParam("scheduleEntry") String scheduleEntry,
                       @RequestParam("arrStation") String arrStation,
                       Model model){
        int scheduleId = Integer.parseInt(scheduleEntry);
        CapsulesScheduleEntity cse = capsuleScheduleService.getScheduleById(scheduleId);
        model.addAttribute("scheduleEntry", cse);
        int capsuleId = cse.getCapsule().getId();
        CapsuleEntity capsule = capsuleService.getCapsuleById(capsuleId);
        int seatsNumber = capsule.getSeatsNumber();
        StationEntity fromStation = cse.getFromStation();


        return "/purchaseTicket";
    }


}
