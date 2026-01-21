package com.Aarthi.expense_tracker.Controller;

import com.Aarthi.expense_tracker.model.User;
import com.Aarthi.expense_tracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserRepository userRepo;
    //private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginpage() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
}

@Autowired
private PasswordEncoder passwordEncoder;

@PostMapping("/register")
public String registerUser(@ModelAttribute User user, Model model) {

   
    if (userRepo.findByEmail(user.getEmail()).isPresent()) {
        model.addAttribute("error", "Email already exists!");
        return "register";
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepo.save(user);

    model.addAttribute("success", "Account created successfully! Please login.");
    return "login";
}




   
}
