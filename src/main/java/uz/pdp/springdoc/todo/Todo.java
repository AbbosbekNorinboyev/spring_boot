package uz.pdp.springdoc.todo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(name = "Todo Entity", description = "Todo Entity Description")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Parameter(name = "user id", description = "Todo ni yaratgan odamning id si", example = "1")
    private Integer userId;
    @Parameter(name = "todo ni nomi", description = "masalan Java ni PDP da o'qish", example = "PDP Online Java Bootcamp")
    private String title;
    private boolean completed;
}