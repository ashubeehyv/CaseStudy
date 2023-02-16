/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Controller;

import com.CaseStudy.Entities.User.User;
import com.CaseStudy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author beehyv
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        User result = userService.getUserById(id);
        return result;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        String result = userService.addUser(user);
        return result;
    }

    @PutMapping("/update/{id}")
    public String modifyUser(@PathVariable("id") int id, @RequestBody User modifiedUser) {
        String result = userService.updateUser(id, modifiedUser);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String result = userService.deleteUser(id);
        return result;
    }
    
}
