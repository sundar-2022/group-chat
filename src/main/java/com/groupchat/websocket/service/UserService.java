package com.groupchat.websocket.service;

import com.groupchat.websocket.dto.request.UserSignupDTO;
import com.groupchat.websocket.models.User;
import com.groupchat.websocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;


    public User createUser(UserSignupDTO userSignupDTO) throws Exception {
        User user = new User();
        user.setName(userSignupDTO.getName());
        String userName = determineUserName(userSignupDTO.getEmail(), userSignupDTO.getMobileNo());
        user.setUserName(userName);
        user.setPassword(bcryptEncoder.encode(userSignupDTO.getPassword()));
        userRepository.save(user);
        return user;
    }


    private String determineUserName(String email, String phone) throws Exception {
        if (isValidPhone(phone)) {
            return phone;
        } else if (isValidEmail(email)) {
            return email;
        } else {
            throw new Exception("Invalid email and phone. Cannot set username.");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhone(String phone) {
        String phoneRegex = "^[+]?\\d{10,13}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        if (phone == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
