package com.Ecommerce.User.Controller;

import com.Ecommerce.User.DTO.LoginRequest;
import com.Ecommerce.User.Entity.Auth;
import com.Ecommerce.User.Enums.Roles;
import com.Ecommerce.User.Repository.AuthRepository;
import com.Ecommerce.User.Util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/Auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository userRepository;
    private final JwtUtil jwtUtil;


    @PostMapping("/register")
    public String register(@RequestBody Auth auth) {
        auth.setPassword(passwordEncoder.encode(auth.getPassword()));

        if (auth.getRole() == null) {
            auth.setRole(Roles.USER);
        }
        userRepository.save(auth);

        return "user registered successfully";
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest)
    {
        System.out.println(loginRequest.getEmail());
        try {

            Auth existuser = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User Not Found"));

            System.out.println("USER FOUND");

            boolean isMatch = passwordEncoder.matches(
                    loginRequest.getPassword(),
                    existuser.getPassword());

            System.out.println("PASSWORD MATCH = " + isMatch);

            if(!isMatch)
            {
                throw new RuntimeException("Invalid Password");
            }

            String token = jwtUtil.generateToken(existuser);

            System.out.println("TOKEN = " + token);

            return token;

        } catch (Exception e) {

            e.printStackTrace();

            return e.getMessage();
        }
    }

}
