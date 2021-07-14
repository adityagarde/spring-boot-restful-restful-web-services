package com.github.adityagarde.rest.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> fetchAllUsers() {
        return service.fetchAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> fetchUserById(@PathVariable Integer id) {
        User user = service.findById(id);
        if (null == user) {
            throw new UserNotFoundException("UserId = " + id);
        } else {
            EntityModel<User> userModel = EntityModel.of(user);
            WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).fetchAllUsers());
            userModel.add(linkToUsers.withRel("all-users"));
            return userModel;
        }
    }

    @PostMapping(path = "/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User newUser = service.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                             .path("/{id}")
                                             .buildAndExpand(newUser.getId())
                                             .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("id = " + id);
    }
}
