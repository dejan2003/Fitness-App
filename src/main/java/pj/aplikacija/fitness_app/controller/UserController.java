package pj.aplikacija.fitness_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pj.aplikacija.fitness_app.entity.User;
import pj.aplikacija.fitness_app.model.UserModel;
import pj.aplikacija.fitness_app.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService service;

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getUserById(id));
    }

    @GetMapping(path = "/name/{name}")
    public List<User> getUserByName(@PathVariable String name) {
        return service.getUserByName(name);
    }

    @PostMapping
    public User createUser(@RequestBody UserModel user) {
        return service.createUser(user);
    }

    @PutMapping(path = "/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody UserModel user) {
        System.out.println(user);
        return service.updateUser(id, user);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);
    }
}
