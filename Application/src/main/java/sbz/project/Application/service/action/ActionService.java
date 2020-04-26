package sbz.project.Application.service.action;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.project.Application.domain.facts.Action;

@Service
public class ActionService {

    private final KieContainer kieContainer;

    @Autowired
    public ActionService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void startSystem() {
        KieSession kieSession = kieContainer.newKieSession();
        Action startAction = new Action("START_SYSTEM");
        kieSession.insert(startAction);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
