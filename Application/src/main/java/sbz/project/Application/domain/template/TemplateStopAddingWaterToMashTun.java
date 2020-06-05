package sbz.project.Application.domain.template;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class  TemplateStopAddingWaterToMashTun extends Template {

    private double water;

    public TemplateStopAddingWaterToMashTun(String name, double water) {
        super(name);
        this.water = water;
    }

}
