package sbz.project.Application.domain.facts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Alarm {

    private String name;
    private String problem;

}
