package pj.aplikacija.fitness_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pj.aplikacija.fitness_app.entity.Exercise;
import pj.aplikacija.fitness_app.model.ExerciseModel;
import pj.aplikacija.fitness_app.repository.ExerciseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository repository;

    public List<Exercise> getAllExcercise() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Exercise> getExerciseById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public Exercise saveExercise(ExerciseModel model) {
        Exercise exercise = new Exercise();
        exercise.setName(model.getName());
        exercise.setCreatedAt(LocalDateTime.now());
        return repository.save(exercise);
    }

    public Exercise updateExercise(Integer id, ExerciseModel model) {
        Exercise exercise = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        exercise.setName(model.getName());
        exercise.setUpdatedAt(LocalDateTime.now());
        return repository.save(exercise);
    }

    public void deleteExercise(Integer id) {
        Exercise exercise = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        exercise.setDeletedAt(LocalDateTime.now());
        repository.save(exercise);
    }
}
