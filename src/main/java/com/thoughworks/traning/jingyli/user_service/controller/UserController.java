package com.thoughworks.traning.jingyli.user_service.controller;
import com.google.common.net.HttpHeaders;
import com.thoughworks.traning.jingyli.user_service.exception.NotFoundException;
import com.thoughworks.traning.jingyli.user_service.model.User;
import com.thoughworks.traning.jingyli.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public User add(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logIn(@RequestBody User user, HttpServletResponse response) throws NotFoundException {
        String token = userService.isLogIn(user);
        response.addHeader("Authentication", token);
        return token;
    }

    @RequestMapping(value = "/verifications", method = RequestMethod.POST)
    public User verifyToken(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("!!!!!!!!!!!!token!!!!!!!!!!!!!!!!!!!!!!!!!!!!  " + token);
        User user = userService.getUserByToken(token);
        System.out.println("!!!!!!!!!!!!User!!!!!!!!!!!!!!!!!!!!!!!!!!!!  " + user.toString());
        return user;
    }

    @GetMapping
    public String hello(){
        System.out.println("Hello Lily ! ");
        return "hello Lily";
    }


}
