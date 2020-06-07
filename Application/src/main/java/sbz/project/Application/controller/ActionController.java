package sbz.project.Application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sbz.project.Application.domain.dto.MeasurerDTO;
import sbz.project.Application.domain.template.TemplatePhase1StopAddingWaterToMashTun;
import sbz.project.Application.service.action.ActionService;

import java.util.ArrayList;

@Controller
@RequestMapping("/api/action")
public class ActionController {

    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping("/measurer")
    public ResponseEntity changeMeasurer(@RequestBody MeasurerDTO measurerDTO) {
        actionService.action(measurerDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/start")
    public ResponseEntity startSystem() {
        try {
            actionService.startSystem();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
