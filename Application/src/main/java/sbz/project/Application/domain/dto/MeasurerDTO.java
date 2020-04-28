package sbz.project.Application.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import sbz.project.Application.domain.enums.Unit;

@Data
@AllArgsConstructor
public class MeasurerDTO{

    private String name;
    private double value;
    private Unit unit;

}
