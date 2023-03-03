package com.CaseStudy.Controller;

import com.CaseStudy.Config.JwtUtil;
import com.CaseStudy.Config.UserDetailServiceImpl;
import com.CaseStudy.Helper.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginCredentials loginCredentials) throws Exception {
        try{
            this.authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(loginCredentials.getUserName(), loginCredentials.getPassword())));

        }
        catch(UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        catch(BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = this.userDetailServiceImpl.loadUserByUsername(loginCredentials.getUserName());
        String token = this.jwtUtil.generateToken(userDetails);
        HashMap<String, String> response = new HashMap<>();
        response.put("token",token);
        return ResponseEntity.ok(response);
    }

}
