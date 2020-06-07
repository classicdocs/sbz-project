package sbz.project.Application.domain.template;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "template")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;

    private String ruleName;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date dateCreated;

    private String ruleValues;

    public Template(String templateName, String ruleName, String ruleValues) {
        this.templateName = templateName;
        this.ruleName = ruleName;
        this.ruleValues = ruleValues;
    }

    public String getNameAndValues() {
        String[] tokens = ruleValues.split(";");
        StringBuilder values = new StringBuilder();
        for(int i = 0; i < tokens.length; i++) {
            values.append(tokens[i].split("=")[1]);
        }

        return ruleName + ";" + values.toString();
    }

}
