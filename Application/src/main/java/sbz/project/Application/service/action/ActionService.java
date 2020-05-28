package sbz.project.Application.service.action;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.project.Application.domain.dto.MeasurerDTO;
import sbz.project.Application.domain.enums.Unit;
import sbz.project.Application.domain.facts.Action;
import sbz.project.Application.domain.facts.Measurer;
import sbz.project.Application.socket.Message;
import sbz.project.Application.socket.MessageProducer;

import java.util.Collection;

@Service
public class ActionService {

    private final KieContainer kieContainer;
    private static KieSession kieSession;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    public ActionService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void startSystem() throws InterruptedException {

        if (kieSession != null) {
            return;
        }

        kieSession = kieContainer.newKieSession("alarmConfigKsessionRealtimeClock");
        Action startAction = new Action("START_SYSTEM");
        kieSession.setGlobal("actionService", this);
        kieSession.insert(startAction);

        boolean working = true;
        while (working) {
            Thread.sleep(1000);
            kieSession.fireAllRules();
            Collection<Action> actions = (Collection<Action>) kieSession.getObjects( new ClassObjectFilter(Action.class));

            for (Action action : actions) {
                if (action.getType().equals("STOP_SYSTEM")) {
                    working = false;
                }
            }

        }
    }

    public void action(MeasurerDTO measurerDTO) {
//        Collection<Measurer> measurers = (Collection<Measurer>) kieSession.getObjects( new ClassObjectFilter(Measurer.class) );

//        Measurer measurer = null;
//
//        for (Measurer m: measurers) {
//            if (m.getName().equals(measurerDTO.getName())) {
//                measurer = m;
//                break;
//            }
//        }
//
//        if (measurer == null) {
//            String name = measurerDTO.getName();
//            Measurer newMeasurer = new Measurer(name, measurerDTO.getValue(), measurerDTO.getUnit());
//            kieSession.insert(newMeasurer);
//        } else {
//            measurer.setValue(measurerDTO.getValue());
//            FactHandle factHandle = kieSession.getFactHandle(measurer);
//            kieSession.update(factHandle, measurer);
//        }
//
        Measurer newMeasurer = new Measurer(measurerDTO.getName(), measurerDTO.getValue(), measurerDTO.getUnit());
        kieSession.insert(newMeasurer);
        int fired = kieSession.fireAllRules();
    }

    public static void startAction(long time, String actionType) {
        try {
            Thread t1 = new Thread();
            t1.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Action action = new Action(actionType);
        kieSession.insert(action);
        kieSession.fireAllRules();
    }

    public void sendMessage(String actionType) {
        messageProducer.sendMessage(new Message(actionType));
    }
}
