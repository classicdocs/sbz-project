package sbz.project.Application.domain.facts;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
public class Action {

    private String type;
    private Date endOfExecution;

    public Action(String type) {
        this.type = type;
    }
}
