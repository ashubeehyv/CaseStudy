/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.dao;

import com.CaseStudy.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author beehyv
 */
public interface UserRepository extends JpaRepository<User,Integer>{
    public boolean existsByEmail(String email);
}
