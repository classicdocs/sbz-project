package sbz.project.Application.domain.facts;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import sbz.project.Application.domain.enums.Unit;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Role(Role.Type.EVENT)
@Expires("1s")
public class Measurer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private double value;
    private Unit unit;

    public Measurer() {
        super();
    }

    public Measurer(String name) {
        this.name = name;
    }

    public Measurer(String name, Unit unit) {
        super();
        this.name = name;
        this.unit = unit;
    }

    public Measurer(String name, Unit unit, double value) {
        super();
        this.name = name;
        this.unit = unit;
        this.value = value;
    }
}
