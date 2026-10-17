package com.example.todo_backend;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    private final TaskRepository repo;
    public TaskController(TaskRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Task> getAll() { return repo.findAll(); }

    @PostMapping
    public Task create(@RequestBody Task task) { return repo.save(task); }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task updated) {
        Task task = repo.findById(id).orElseThrow();
        if (updated.getText() != null) task.setText(updated.getText());
        task.setCompleted(updated.isCompleted());
        return repo.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}