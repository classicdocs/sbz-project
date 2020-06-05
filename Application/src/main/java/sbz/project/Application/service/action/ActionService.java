package sbz.project.Application.service.action;

import org.kie.api.runtime.ClassObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.project.Application.domain.dto.MeasurerDTO;
import sbz.project.Application.domain.facts.Action;
import sbz.project.Application.domain.facts.Measurer;
import sbz.project.Application.service.rule.RuleService;
import sbz.project.Application.socket.Message;
import sbz.project.Application.socket.MessageProducer;

import java.util.Collection;

@Service
public class ActionService {

    @Autowired
    private MessageProducer messageProducer;


    public void startSystem() throws InterruptedException {
        RuleService.kieSession.getAgenda().getAgendaGroup("initMeasurers").setFocus();
        RuleService.kieSession.fireAllRules();

        Action startAction = new Action("START_SYSTEM");
        RuleService.kieSession.insert(startAction);

        boolean working = true;
        while (working) {
            Thread.sleep(500);
            RuleService.kieSession.getAgenda().getAgendaGroup("stopSystem").setFocus();
            RuleService.kieSession.fireAllRules();
            Collection<Action> actions = (Collection<Action>) RuleService.kieSession.getObjects( new ClassObjectFilter(Action.class));

            for (Action action : actions) {
                if (action.getType().equals("STOP_SYSTEM")) {
                    working = false;
                }
            }

        }
    }

    public void action(MeasurerDTO measurerDTO) {
        Measurer newMeasurer = new Measurer(measurerDTO.getName(), measurerDTO.getValue(), measurerDTO.getUnit());
        RuleService.kieSession.insert(newMeasurer);
        int fired = RuleService.kieSession.fireAllRules();
    }

    public static void startAction(long time, String actionType) {
        try {
            Thread t1 = new Thread();
            t1.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Action action = new Action(actionType);
        RuleService.kieSession.insert(action);
        RuleService.kieSession.fireAllRules();
    }

    public void sendMessage(String actionType) {
        messageProducer.sendMessage(new Message(actionType));
    }
}
