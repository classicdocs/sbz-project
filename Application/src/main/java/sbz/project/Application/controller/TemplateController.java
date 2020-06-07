package sbz.project.Application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sbz.project.Application.domain.template.Template;
import sbz.project.Application.service.template.TemplateService;

import java.util.List;

@Controller
@RequestMapping("/api/template")
public class TemplateController {

    private TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/all")
    public ResponseEntity get() {

        List<Template> templates = templateService.getTemplates();
        return new ResponseEntity(templates, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Template template) {

        try {
            templateService.update(template);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
