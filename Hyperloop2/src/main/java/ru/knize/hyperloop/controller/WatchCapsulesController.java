package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knize.hyperloop.DTO.CapsuleScheduleDTO;
import ru.knize.hyperloop.entities.CapsuleEntity;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.services.CapsuleScheduleService;
import ru.knize.hyperloop.services.CapsuleService;
import ru.knize.hyperloop.services.StationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knize on 30.09.16.
 */
@Controller
public class WatchCapsulesController {


    @Autowired
    CapsuleService capsuleService;
    @Autowired
    CapsuleScheduleService capsuleScheduleService;
    @Autowired
    StationService stationService;

    @GetMapping("/cms/watchCapsules")
    public String initWatchCapsules(@RequestParam(name = "capsule", defaultValue = "not_set") String capsuleStr, Model model) {
        List<CapsulesScheduleEntity> scheduleList;
        CapsuleEntity capsule = null;
        try {
            int capsuleId = Integer.parseInt(capsuleStr);
            scheduleList = capsuleScheduleService.getByCapsuleId(capsuleId);
            capsule = capsuleService.getCapsuleById(capsuleId);
        } catch (NumberFormatException e) {
            scheduleList = new ArrayList<CapsulesScheduleEntity>();
        }

        List<CapsuleEntity> capsuleList = capsuleService.getCapsuleList();
        List<StationEntity> stationList = stationService.getStations();
        model.addAttribute("stationList", stationList);
        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute("capsuleList", capsuleList);
        model.addAttribute("selectedCapsule", capsule);
        return "/cms/watchCapsules";
    }

    @PostMapping("/cms/watchCapsules")
    public String postWatchCapsules(@RequestParam(name = "stationId") String stationStr,
                                    @RequestParam(name = "date") String dateStr,
                                    @RequestParam(name = "time") String timeStr,
                                    @RequestParam(name = "direction") String directionStr,
                                    @RequestParam(name = "capsuleIdHidden") String capsuleIdStr,
                                    Model model) {
        CapsuleScheduleDTO csDTO = new CapsuleScheduleDTO();
        csDTO.setStationId(stationStr);
        csDTO.setDate(dateStr);
        csDTO.setTime(timeStr);
        csDTO.setDirection(directionStr);
        csDTO.setCapsuleId(capsuleIdStr);
        // create sequence and persist it

        System.out.println("Date: " + dateStr);

        return "/cms/watchCapsules";
    }
}
