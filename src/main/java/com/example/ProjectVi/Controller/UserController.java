package com.example.ProjectVi.Controller;

import com.example.ProjectVi.Model.User;
import com.example.ProjectVi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping
    public ResponseEntity sendOTP(@RequestParam String email){
        userService.sendOTP(email);
        return new ResponseEntity("Sent", HttpStatusCode.valueOf(200));
    }
    @PostMapping("/add")
    public ResponseEntity createUser(@RequestBody User user,@RequestParam String otp) {
        try {
            User createdUser = userService.createUser(user,otp);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable int id) {
        Optional<User> user1 = userService.getUser(id);
        if (user1.isPresent()) {
            return ResponseEntity.ok(user1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public  ResponseEntity updateUser(@PathVariable int id,@RequestBody String newName) throws Exception {
        userService.updateUser(id,newName);
        return new ResponseEntity("updated",HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return new ResponseEntity("deleted",HttpStatusCode.valueOf(200));
    }
}
