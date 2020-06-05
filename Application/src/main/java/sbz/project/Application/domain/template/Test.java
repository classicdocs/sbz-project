package sbz.project.Application.domain.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Test {
    private double value;

    public Test(double value) {
        this.value = value;
    }
}
