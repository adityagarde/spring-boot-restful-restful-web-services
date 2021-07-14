package com.github.adityagarde.rest.restfulwebservices.post;

import com.github.adityagarde.rest.restfulwebservices.user.User;
import com.github.adityagarde.rest.restfulwebservices.user.UserNotFoundException;
import com.github.adityagarde.rest.restfulwebservices.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostJPAController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> fetchAllPostsByUserId(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("UserId = " + id);
        else
            return user.get().getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id, @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("UserId = " + id);

        post.setUser(user.get());
        postRepository.save(post);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                             .path("/{id}")
                                             .buildAndExpand(post.getId())
                                             .toUri();

        return ResponseEntity.created(uri).build();
    }

}