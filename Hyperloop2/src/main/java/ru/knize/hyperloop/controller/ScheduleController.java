package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.services.CapsuleScheduleService;
import ru.knize.hyperloop.services.StationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knize on 05.10.16.
 */
@Controller
public class ScheduleController {

    @Autowired
    StationService stationService;
    @Autowired
    CapsuleScheduleService capsuleScheduleService;

    @GetMapping("/schedule")
    public String init(
            @RequestParam(value = "stationId", defaultValue = "0") String selectedStationStr,
            Model model){
        int selectedStationId = -1;
        try {
            selectedStationId = Integer.parseInt(selectedStationStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        List<CapsulesScheduleEntity> cseList;
        if(selectedStationId != -1){
            cseList = capsuleScheduleService.getByStationId(selectedStationId);
        }else {
            cseList = new ArrayList<>();
        }

        List<StationEntity> stations = stationService.getStations();
        model.addAttribute("stationList", stations);
        model.addAttribute("scheduleList", cseList);
        return "schedule";
    }
}
