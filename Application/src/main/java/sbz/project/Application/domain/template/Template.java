package sbz.project.Application.domain.template;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;

    private String ruleName;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date dateCreated;

    public Template(String templateName, String ruleName) {
        this.templateName = templateName;
        this.ruleName = ruleName;
    }

}
