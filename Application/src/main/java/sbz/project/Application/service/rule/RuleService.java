package sbz.project.Application.service.rule;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import sbz.project.Application.exceptions.DrlException;
import sbz.project.Application.service.action.ActionService;
import sbz.project.Application.service.template.TemplateService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
public class RuleService {

    public static KieSession kieSession;
    private final KieHelper kieHelper;
    private final ActionService actionService;
    private final KieBaseConfiguration kieBaseConfiguration;
    private final ResourcePatternResolver resolver;
    private final TemplateService templateService;

    @Autowired
    public RuleService(KieHelper kieHelper, ActionService actionService,
                       KieBaseConfiguration kieBaseConfiguration,
                       ResourcePatternResolver resolver, TemplateService templateService) throws IOException {
        this.kieHelper = kieHelper;
        this.actionService = actionService;
        this.kieBaseConfiguration = kieBaseConfiguration;
        this.resolver = new PathMatchingResourcePatternResolver(RuleService.class.getClassLoader());
        this.templateService = templateService;

        this.loadStaticRules();
        this.loadDynamicRules();
        this.updateSession();
    }

    private void loadStaticRules() throws IOException {

        Resource[] resources = getResources("/sbz.project/rules/*");

        for (Resource resource: resources) {
            String content = null;
            content = Files.asCharSource(resource.getFile(), Charsets.UTF_8).read();
            this.kieHelper.addContent(content, ResourceType.DRL);
        }
    }

    private Resource[] getResources(String path) throws IOException {
        return this.resolver.getResources(path);
    }

    private Resource getResource(String path) throws IOException {
        return this.resolver.getResource(path);
    }

    private void loadDynamicRules() throws IOException {

        List<String> drls = templateService.getDrls();

        for (String drl : drls) {
            this.kieHelper.addContent(drl, ResourceType.DRL);
        }

        try {
            this.verifyRules();
        } catch (DrlException e) {
            e.printStackTrace();
        }

    }

    public void addRule(String drl) throws DrlException {
        this.kieHelper.addContent(drl, ResourceType.DRL);
        this.verifyRules();

    }

    public void removeRule(String ruleName) {
        kieSession.getKieBase().removeRule("templates", ruleName);
    }

    private void verifyRules() throws DrlException {

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new DrlException();
        }
    }


    private void updateSession() {
        kieSession = this.kieHelper.build(this.kieBaseConfiguration).newKieSession();
        this.insertServices();

        long ruleCount = kieSession.getKieBase()
                .getKiePackages()
                .stream()
                .map(KiePackage::getRules)
                .map(Collection::size)
                .reduce(0, Integer::sum);
        System.out.println("Loaded " + ruleCount + " rules into Drools engine.");

    }

    private void insertServices() {
        kieSession.setGlobal("actionService", this.actionService);
    }

}
