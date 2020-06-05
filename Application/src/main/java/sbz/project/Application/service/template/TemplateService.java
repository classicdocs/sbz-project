package sbz.project.Application.service.template;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.ObjectDataCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import sbz.project.Application.domain.template.Template;
import sbz.project.Application.domain.template.TemplateStopAddingWaterToMashTun;
import sbz.project.Application.domain.template.Test;
import sbz.project.Application.repository.template.TemplateStopAddingWaterToMashTunRepository;
import sbz.project.Application.service.rule.RuleService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {

    private final TemplateStopAddingWaterToMashTunRepository templateStopAddingWaterToMashTunRepository;
//    private final ObjectDataCompiler converter;
    private final ResourcePatternResolver resolver;

    @Autowired
    public TemplateService(TemplateStopAddingWaterToMashTunRepository templateStopAddingWaterToMashTunRepository) {
        this.templateStopAddingWaterToMashTunRepository = templateStopAddingWaterToMashTunRepository;
//        this.converter = converter;
        resolver = new PathMatchingResourcePatternResolver(TemplateService.class.getClassLoader());
    }

    public List<String> getDrls() throws IOException {

        List<String> drls = new ArrayList<>();
        String stopAddingWaterToMashTun = getDrlFromStopAddingWaterToMashTun();
        if (stopAddingWaterToMashTun != "") {
            drls.add(stopAddingWaterToMashTun);
        }

        return drls;
    }

    public String getDrlFromStopAddingWaterToMashTun() throws IOException {
        List<TemplateStopAddingWaterToMashTun> templateStopAddingWaterToMashTunList = templateStopAddingWaterToMashTunRepository.findAll();

        if (templateStopAddingWaterToMashTunList.isEmpty()) {
            return "";
        }

        TemplateStopAddingWaterToMashTun templateStopAddingWaterToMashTun = templateStopAddingWaterToMashTunList.get(templateStopAddingWaterToMashTunList.size() - 1);

        String templateName = templateStopAddingWaterToMashTun.getName();
        String path = "/sbz.project/templates/template-" + templateName + ".drt";

        InputStream template = TemplateService.class.getResourceAsStream(path);

        ResourcePatternResolver resolver =  new PathMatchingResourcePatternResolver(RuleService.class.getClassLoader());
        InputStream template2 = resolver.getResource(path).getInputStream();

        List<TemplateStopAddingWaterToMashTun> data = new ArrayList<>();
        data.add(new TemplateStopAddingWaterToMashTun(templateName, templateStopAddingWaterToMashTun.getWater()));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template2);

        System.out.println(drl);

        return drl;
    }
}
