package pj.aplikacija.fitness_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pj.aplikacija.fitness_app.entity.MuscleGroup;

import java.util.List;
import java.util.Optional;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Integer> {

    List<MuscleGroup> findAllByDeletedAtIsNull();

    Optional<MuscleGroup> findByIdAndDeletedAtIsNull(Integer id);
}
