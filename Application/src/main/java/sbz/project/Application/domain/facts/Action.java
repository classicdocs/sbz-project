package sbz.project.Application.domain.facts;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
@Role(Role.Type.EVENT)
@Expires("1h")
public class Action {

    private String type;
    private Date endOfExecution;

    public Action(String type) {
        this.type = type;
    }
}
