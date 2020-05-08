package sbz.project.Application.domain.facts;

import lombok.AllArgsConstructor;
import lombok.Data;
import sbz.project.Application.domain.enums.Unit;

@Data
@AllArgsConstructor
public class Measurer {

    private String name;
    private double value;
    private Unit unit;

    public Measurer(String name, Unit unit) {
        this.name = name;
        this.unit = unit;
    }

    public Measurer(String name, Unit unit, double value) {
        this.name = name;
        this.unit = unit;
        this.value = value;
    }
}
