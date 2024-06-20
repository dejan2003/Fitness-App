package pj.aplikacija.fitness_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pj.aplikacija.fitness_app.entity.Exercise;
import pj.aplikacija.fitness_app.model.ExerciseModel;
import pj.aplikacija.fitness_app.service.ExerciseService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/exercise")
public class ExerciseController {

    private final ExerciseService service;

    @GetMapping
    public List<Exercise> getAll() {
        return service.getAllExcercise();
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Exercise> getById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getExerciseById(id));
    }

    @PostMapping
    public Exercise create(@RequestBody ExerciseModel model) {
        return service.saveExercise(model);
    }

    @PutMapping(path = "/{id}")
    public Exercise update(@PathVariable Integer id, @RequestBody ExerciseModel model) {
        return service.updateExercise(id, model);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.deleteExercise(id);
    }
}
