/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Controller;

import com.CaseStudy.Entities.User.User;
import com.CaseStudy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;


/**
 *
 * @author beehyv
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{getUserByEmail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("getUserByEmail") String email){
        System.out.println("Hi");
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }


//    @PostMapping("/addUser")
//    public String addUser(@RequestBody User user) {
//        return userService.addUser(user);
//    }

    @PutMapping("/update/{id}")
    public String modifyUser(@PathVariable("id") int id, @RequestBody User modifiedUser) {
        return userService.updateUser(id, modifiedUser);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }
    
}
