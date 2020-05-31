package sbz.project.Application.domain.facts;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import sbz.project.Application.domain.enums.Unit;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@Role(Role.Type.EVENT)
@Expires("10s")
public class Measurer implements Serializable{

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

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Measurer measurer = (Measurer) o;
//        return Double.compare(measurer.value, value) == 0 &&
//                Objects.equals(name, measurer.name) &&
//                unit == measurer.unit;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, unit);
    }
}
