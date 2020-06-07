package sbz.project.Application.service.template;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import sbz.project.Application.domain.template.Template;
import sbz.project.Application.repository.template.TemplateRepository;
import sbz.project.Application.service.rule.RuleService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;
    private RuleService ruleService;

    @Autowired
    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Autowired
    public void setRuleService(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    public List<String> getDrls() throws IOException {

        List<String> drls = new ArrayList<>();

        List<Template> templates = templateRepository.findAll();

        for(Template template : templates) {
            String drl = createDrlFromTemplate(template);
            drls.add(drl);
        }

        return drls;

    }

    private String createDrlFromTemplate(Template template) throws IOException {
        String templateName = template.getTemplateName();
        String path = "/sbz.project/templates/template-" + templateName + ".drt";

        ResourcePatternResolver resolver =  new PathMatchingResourcePatternResolver(RuleService.class.getClassLoader());
        InputStream inputStream = resolver.getResource(path).getInputStream();

        String[] values = template.getNameAndValues().split(";");

        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
                values
        });

        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, inputStream);

        System.out.println(drl);

        return drl;
    }

    public List<Template> getTemplates() {

        List<Template> templates = new ArrayList<>();
        templates.addAll(templateRepository.findAll());

        return templates;
    }

    public void update(Template template) throws IOException {
        templateRepository.save(template);
        ruleService.load();
    }
}
