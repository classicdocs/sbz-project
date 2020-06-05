package sbz.project.Application.domain.template;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TemplatePhase1StopAddingWaterToMashTun extends Template {

    private double water;

    public TemplatePhase1StopAddingWaterToMashTun(String templateName, String ruleName, double water) {
        super(templateName, ruleName);
        this.water = water;
    }

}
