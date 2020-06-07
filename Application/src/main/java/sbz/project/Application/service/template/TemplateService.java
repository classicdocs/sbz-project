package sbz.project.Application.service.template;

import org.drools.template.ObjectDataCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import sbz.project.Application.domain.template.Template;
import sbz.project.Application.domain.template.TemplatePhase1StopAddingWaterToMashTun;
import sbz.project.Application.exceptions.DrlException;
import sbz.project.Application.repository.template.TemplatePhase1StopAddingWaterToMashTunRepository;
import sbz.project.Application.service.rule.RuleService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static sbz.project.Application.contants.TemplateNames.PHASE_1_STOP_ADDING_WATER_TO_MASH_TUN;

@Service
public class TemplateService {

    private final TemplatePhase1StopAddingWaterToMashTunRepository templatePhase1StopAddingWaterToMashTunRepository;
    private final ResourcePatternResolver resolver;
    private RuleService ruleService;

    @Autowired
    public TemplateService(TemplatePhase1StopAddingWaterToMashTunRepository templatePhase1StopAddingWaterToMashTunRepository) {
        this.templatePhase1StopAddingWaterToMashTunRepository = templatePhase1StopAddingWaterToMashTunRepository;
        resolver = new PathMatchingResourcePatternResolver(TemplateService.class.getClassLoader());
    }
    public void setRuleService(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    public List<String> getDrls() throws IOException {

        List<String> drls = new ArrayList<>();
        String stopAddingWaterToMashTun = getDrlFromPhase1StopAddingWaterToMashTun();
        if (stopAddingWaterToMashTun != "") {
            drls.add(stopAddingWaterToMashTun);
        }

        return drls;
    }

    public String getDrlFromPhase1StopAddingWaterToMashTun() throws IOException {
        List<TemplatePhase1StopAddingWaterToMashTun> templatePhase1StopAddingWaterToMashTunList = templatePhase1StopAddingWaterToMashTunRepository.findAll();

        if (templatePhase1StopAddingWaterToMashTunList.isEmpty()) {
            return "";
        }

        TemplatePhase1StopAddingWaterToMashTun templatePhase1StopAddingWaterToMashTun = templatePhase1StopAddingWaterToMashTunList.get(templatePhase1StopAddingWaterToMashTunList.size() - 1);

        String templateName = templatePhase1StopAddingWaterToMashTun.getTemplateName();
        String path = "/sbz.project/templates/template-" + templateName + ".drt";

        InputStream template = TemplateService.class.getResourceAsStream(path);

        ResourcePatternResolver resolver =  new PathMatchingResourcePatternResolver(RuleService.class.getClassLoader());
        InputStream template2 = resolver.getResource(path).getInputStream();

        List<TemplatePhase1StopAddingWaterToMashTun> data = new ArrayList<>();
        data.add(new TemplatePhase1StopAddingWaterToMashTun(templateName, templatePhase1StopAddingWaterToMashTun.getRuleName(),
                templatePhase1StopAddingWaterToMashTun.getWater()));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template2);

        System.out.println(drl);

        return drl;
    }

    public List<Template> getTemplates() {

        List<Template> templates = new ArrayList<>();
        templates.addAll(templatePhase1StopAddingWaterToMashTunRepository.findAll());

        return templates;
    }

    public void update(Template template) throws IOException, DrlException {

        String drl = "";
        switch (template.getTemplateName()) {
            case PHASE_1_STOP_ADDING_WATER_TO_MASH_TUN: {

                TemplatePhase1StopAddingWaterToMashTun t = new TemplatePhase1StopAddingWaterToMashTun((TemplatePhase1StopAddingWaterToMashTun) template);
                templatePhase1StopAddingWaterToMashTunRepository.save(t);
                drl = this.getDrlFromPhase1StopAddingWaterToMashTun();
            }
        }

        ruleService.removeRule(template.getRuleName());
        ruleService.addRule(drl);
    }
}
