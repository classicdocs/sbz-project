package sbz.project.Application.service.template;

import org.drools.template.ObjectDataCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import sbz.project.Application.domain.template.TemplatePhase1StopAddingWaterToMashTun;
import sbz.project.Application.repository.template.TemplateStopAddingWaterToMashTunRepository;
import sbz.project.Application.service.rule.RuleService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateService {

    private final TemplateStopAddingWaterToMashTunRepository templateStopAddingWaterToMashTunRepository;
    private final ResourcePatternResolver resolver;

    @Autowired
    public TemplateService(TemplateStopAddingWaterToMashTunRepository templateStopAddingWaterToMashTunRepository) {
        this.templateStopAddingWaterToMashTunRepository = templateStopAddingWaterToMashTunRepository;
        resolver = new PathMatchingResourcePatternResolver(TemplateService.class.getClassLoader());
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
        List<TemplatePhase1StopAddingWaterToMashTun> templatePhase1StopAddingWaterToMashTunList = templateStopAddingWaterToMashTunRepository.findAll();

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
}
