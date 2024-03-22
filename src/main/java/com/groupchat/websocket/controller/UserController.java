package com.groupchat.websocket.controller;


import com.groupchat.websocket.dto.request.UserSignupDTO;
import com.groupchat.websocket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<Object> signupUser(@RequestBody UserSignupDTO userSignupDTO) throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userSignupDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
}
