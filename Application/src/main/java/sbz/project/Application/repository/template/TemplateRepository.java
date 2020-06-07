package sbz.project.Application.repository.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sbz.project.Application.domain.template.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

}
