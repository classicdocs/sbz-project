package sbz.project.Application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sbz.project.Application.service.action.ActionService;

@Controller
@RequestMapping("/api/action")
public class ActionController {

    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping("/start")
    public ResponseEntity startSystem() {
        actionService.startSystem();

        return new ResponseEntity(HttpStatus.OK);
    }
}
