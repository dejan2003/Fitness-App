package pj.aplikacija.fitness_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pj.aplikacija.fitness_app.entity.Exercise;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    List<Exercise> findAllByDeletedAtIsNull();

    Optional<Exercise> findByIdAndDeletedAtIsNull(Integer id);
}
