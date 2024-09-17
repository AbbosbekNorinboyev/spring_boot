package uz.pdp.springdoc.todo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdoc.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo Controller", description = "This controller was created for playing with todo entity (Ushbu controller todo entity si bilan ishlash uchun yaratilgan)")
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Operation(summary = "Id bo'yicha todo ni topadigan endpoint",
            description = "Id bo'yicha todo ni topadigan endpoint (description)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berilgan id bo'yicha Todo topildi",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Todo.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Berilgan id bo'yicha Todo topilmadi",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = EntityNotFoundException.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server da xatolik",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class))
                    })
    })
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo Not Found With ID: " + id));
        return ResponseEntity.ok(todo);
    }

    @Operation(summary = "Todo larni hammasini oladigan endpoint")
    @GetMapping("/")
    public ResponseEntity<List<Todo>> getAllTodo(TodoCriteria todoCriteria) {
        System.out.println("todoCriteria: " + todoCriteria);
        List<Todo> todos = todoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    @Operation(summary = "Todo ni id bo'yicha data base dan o'chirish", deprecated = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo Not Found With ID: " + id));
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoRepository.save(todo));
    }
}