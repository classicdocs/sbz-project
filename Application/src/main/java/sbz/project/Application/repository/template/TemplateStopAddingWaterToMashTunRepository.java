package sbz.project.Application.repository.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sbz.project.Application.domain.template.TemplateStopAddingWaterToMashTun;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateStopAddingWaterToMashTunRepository  extends JpaRepository<TemplateStopAddingWaterToMashTun, Long> {

}
