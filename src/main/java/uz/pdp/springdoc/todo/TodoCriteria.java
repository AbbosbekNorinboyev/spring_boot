package uz.pdp.springdoc.todo;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springdoc.core.annotations.ParameterObject;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@ParameterObject
public class TodoCriteria {
    private String title;
    @Parameter(required = true)
    private Type type;
    private boolean completed;
    @Parameter(required = true, example = "2024-01-10 01:01:25")
    private LocalDateTime createdDate;
    @Parameter(required = true, example = "20")
    @Min(18)
    private Integer userID;

    public enum Type {
        EDUCATION, IT, SPORT
    }
}
