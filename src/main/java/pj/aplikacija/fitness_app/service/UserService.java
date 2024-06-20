package pj.aplikacija.fitness_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pj.aplikacija.fitness_app.entity.User;
import pj.aplikacija.fitness_app.model.UserModel;
import pj.aplikacija.fitness_app.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<User> getUserById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public List<User> getUserByName(String name) {
        return repository.findByNameContainsAndDeletedAtIsNull(name);
    }

    public User createUser(UserModel model) {
        User user = new User();
        user.setName(model.getName());
        user.setSurname(model.getSurname());
        user.setCreatedAt(LocalDateTime.now());
        return repository.save(user);
    }

    public User updateUser(Integer id, UserModel model) {
        User user = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        user.setName(model.getName());
        user.setSurname(model.getSurname());
        user.setUpdatedAt(LocalDateTime.now());
        return repository.save(user);
    }

    public void deleteUser(Integer id) {
        User user = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        user.setDeletedAt(LocalDateTime.now());
        repository.save(user);
    }
}
