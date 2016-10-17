package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knize.hyperloop.DTO.CapsuleScheduleDTO;
import ru.knize.hyperloop.entities.BranchEntity;
import ru.knize.hyperloop.entities.CapsuleEntity;
import ru.knize.hyperloop.entities.CapsulesScheduleEntity;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.services.BranchService;
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
    @Autowired
    BranchService branchService;

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
        List<StationEntity> stationList = stationService.getEndStations();
        List<BranchEntity> branchList = branchService.getBranches();
        model.addAttribute("stationList", stationList);
        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute("capsuleList", capsuleList);
        model.addAttribute("branchList", branchList);
        model.addAttribute("selectedCapsule", capsule);
        return "/cms/watchCapsules";
    }

    @PostMapping("/cms/watchCapsules")
    public String postWatchCapsules(@RequestParam(name = "fromStationId") String stationStr,
                                    @RequestParam(name = "date") String dateStr,
                                    @RequestParam(name = "time") String timeStr,
                                    @RequestParam(name = "capsuleIdHidden") String capsuleIdStr,
                                    Model model) {
        CapsuleScheduleDTO csDTO = new CapsuleScheduleDTO();
        csDTO.setStationId(stationStr);
        csDTO.setDepartureTime(dateStr + " " + timeStr);
        csDTO.setCapsuleId(capsuleIdStr);
        capsuleScheduleService.fillSchedule(csDTO);

        System.out.println("Date: " + dateStr);

        return "/cms/watchCapsules";
    }
}
