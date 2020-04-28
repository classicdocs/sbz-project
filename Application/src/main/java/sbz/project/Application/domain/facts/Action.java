package sbz.project.Application.domain.facts;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static sbz.project.Application.service.action.ActionService.startAction;


@AllArgsConstructor
@Getter
@Setter
public class Action {

    private String type;
    private Date endOfExecution;

    public Action(String type) {
        this.type = type;
    }

    public void startAction(long time, String actionType) {
        startAction(time,actionType);
    }

}
