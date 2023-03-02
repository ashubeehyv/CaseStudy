package com.CaseStudy.Config;

import com.CaseStudy.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!userRepository.existsByEmail(username)){
            throw new UsernameNotFoundException("User does not exist!!");
        }
        CustomUserDetail customUserDetail = new CustomUserDetail(userRepository.findByEmail(username));
        return customUserDetail;

//        if(username.equals("Ashutosh")){
//            return new User("Ashutosh","password",new ArrayList<>());
//        }
//        else{
//            throw new UsernameNotFoundException("User not found!!");
//        }

    }
}
