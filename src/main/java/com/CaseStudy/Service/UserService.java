/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Service;


import com.CaseStudy.Entities.User.User;
import com.CaseStudy.dao.CartRepository;
import com.CaseStudy.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author beehyv
 */
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public User getUserById(int id) {
        return userRepository.findById(id).get();

    }

    public String addUser(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            User result = userRepository.save(user);
            return "User successfully added!!" + result;
        }
        else{
            return "User all ready exist!!";
        }

        
    }

    public String updateUser(int id, User modifiedUser) {

        if (userRepository.existsById(id)) {
            User result = userRepository.save(modifiedUser);
            return "User successfully updated!!" + result;
        } else {
            return "User does not exist, so it can't be updated!!";
        }

    }

    public String deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            if(cartRepository.findByUserId(id) != null){
                cartRepository.delete(cartRepository.findByUserId(id));
            }
            return "User successfully deleted!!";
        } else {
            return "User does not exist, so it can't be deleted!!";
        }

    }

}
