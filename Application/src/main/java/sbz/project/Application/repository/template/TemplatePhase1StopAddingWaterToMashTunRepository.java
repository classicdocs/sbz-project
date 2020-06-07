package sbz.project.Application.repository.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sbz.project.Application.domain.template.TemplatePhase1StopAddingWaterToMashTun;

@Repository
public interface TemplatePhase1StopAddingWaterToMashTunRepository extends JpaRepository<TemplatePhase1StopAddingWaterToMashTun, Long> {

}
