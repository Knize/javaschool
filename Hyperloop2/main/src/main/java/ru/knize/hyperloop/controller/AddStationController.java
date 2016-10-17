package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knize.hyperloop.entities.BranchEntity;
import ru.knize.hyperloop.entities.StationEntity;
import ru.knize.hyperloop.services.BranchService;
import ru.knize.hyperloop.services.StationService;

import java.util.List;

/**
 * Created by knize on 04.10.16.
 */
@Controller
public class AddStationController {
    @Autowired
    StationService stationService;
    @Autowired
    BranchService branchService;

    @GetMapping("/cms/addStation")
    public String initAddStation(@RequestParam(value = "selectedBranch", defaultValue = "0") String selectedBranchIdStr, Model model) {

        BranchEntity selectedBranch = branchService.getBranchById(
                Integer.parseInt(selectedBranchIdStr));
        List<BranchEntity> branches = branchService.getBranches();
        List<StationEntity> stations = stationService.getStations();
        model.addAttribute("branchList", branches);
        model.addAttribute("selectedBranch", selectedBranch);
        model.addAttribute("stationList", stations);
        return "/cms/addStation";
    }


}
