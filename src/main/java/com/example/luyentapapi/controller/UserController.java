package com.example.luyentapapi.controller;

import com.example.luyentapapi.model.User;
import com.example.luyentapapi.repository.IUserRepository;
import com.example.luyentapapi.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping
    private ResponseEntity<Iterable<User>> findAll(){
        Iterable<User> userIterable= userService.findAll();
        return new ResponseEntity<>(userIterable, HttpStatus.OK);
    }
    @GetMapping("{/id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        Optional<User>userOptional=userService.findById(id);
      return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User>save(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> saveEdit(@PathVariable Long id,@RequestBody User user){
        Optional<User>userOptional=userService.findById(id);
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){
        Optional<User>userOptional=userService.findById(id);
        if(!userOptional.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
