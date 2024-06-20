package pj.aplikacija.fitness_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pj.aplikacija.fitness_app.entity.MuscleGroup;
import pj.aplikacija.fitness_app.model.MuscleGroupModel;
import pj.aplikacija.fitness_app.repository.MuscleGroupRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MuscleGroupService {

    private final MuscleGroupRepository repository;

    public List<MuscleGroup> getAllMuscleGroup() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<MuscleGroup> getMuscleGroupById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public MuscleGroup saveMuscleGroup(MuscleGroupModel model) {
        MuscleGroup muscleGroup = new MuscleGroup();
        muscleGroup.setName(model.getName());
        muscleGroup.setCreatedAt(LocalDateTime.now());
        return repository.save(muscleGroup);
    }

    public MuscleGroup updateMuscleGroup(Integer id, MuscleGroupModel model) {
        MuscleGroup muscleGroup = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        muscleGroup.setName(model.getName());
        muscleGroup.setUpdatedAt(LocalDateTime.now());
        return repository.save(muscleGroup);
    }

    public void deleteMuscleGroup(Integer id) {
        MuscleGroup muscleGroup = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        muscleGroup.setDeletedAt(LocalDateTime.now());
        repository.save(muscleGroup);
    }
}
