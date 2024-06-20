package pj.aplikacija.fitness_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pj.aplikacija.fitness_app.entity.MuscleGroup;
import pj.aplikacija.fitness_app.model.MuscleGroupModel;
import pj.aplikacija.fitness_app.service.MuscleGroupService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/muscle-group")
public class MuscleGroupController {

    private final MuscleGroupService service;

    @GetMapping
    public List<MuscleGroup> getAll() {
        return service.getAllMuscleGroup();
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<MuscleGroup> getById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getMuscleGroupById(id));
    }

    @PostMapping
    public MuscleGroup create(@RequestBody MuscleGroupModel model) {
        return service.saveMuscleGroup(model);
    }

    @PutMapping(path = "/{id}")
    public MuscleGroup update(@PathVariable Integer id, @RequestBody MuscleGroupModel model) {
        return service.updateMuscleGroup(id, model);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.deleteMuscleGroup(id);
    }
}
