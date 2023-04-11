package com.gary.springsecurityjwt.controller;

import com.gary.springsecurityjwt.entity.AuthRequest;
import com.gary.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Java World!";
    }

    @PostMapping("/authenticate")
    public String getenerateToken(@RequestBody AuthRequest request) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        }catch(Exception ex) {
            throw new Exception("Invalid username or password!");
        }
        return jwtUtil.generateToken(request.getUserName());
    }
}
