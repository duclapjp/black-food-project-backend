package com.example.demo.controller;

import com.example.demo.dto.request.SignInForm;
import com.example.demo.dto.request.SignUpForm;
import com.example.demo.dto.response.JwtResponse;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.*;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.userprincal.UserPrinciple;
import com.example.demo.service.extend.IGeneralStatusService;
import com.example.demo.service.impl.MailServiceImpl;
import com.example.demo.service.impl.RoleServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    IGeneralStatusService generalStatusService;





    @Autowired
    private MailServiceImpl mailService;

    @GetMapping("/verify/{id}")
    public ResponseEntity<?> verifyAccount(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(new ResponMessage("Not found!"), HttpStatus.NOT_FOUND);
        }
        else {
            User user = userOptional.get();
            user.setStatus(generalStatusService.findById(2L).get());
            userService.save(user);
            return new ResponseEntity<>(new ResponMessage("Verify Account is Success, Please login to Use!"),HttpStatus.OK);
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
        if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponMessage("Username was exists!"), HttpStatus.OK);
        }
        if(userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponMessage("Email was exists!"), HttpStatus.OK);
        }
        if(signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()){
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/chinhbeo-18d3b.appspot.com/o/avatar.png?alt=media&token=3511cf81-8df2-4483-82a8-17becfd03211");
        }
        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(),passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
                            ()-> new RuntimeException("Role not found")
                    );
                    roles.add(adminRole);
                    break;
                case "merchant":
                    Role merchantRole = roleService.findByName(RoleName.MERCHANT).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(merchantRole);
                    break;
                case "shipper":
                    Role shipperRole = roleService.findByName(RoleName.SHIPPER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(shipperRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
//        set status la cho duyet dang ky
        user.setStatus(generalStatusService.findById(1L).get());
        user.setRoles(roles);
        userService.save(user);
        Long userId = userService.findByUsername(signUpForm.getUsername()).get().getId();
        Mail mail = new Mail("YOU-WELCOME-BLACK-FOOD", signUpForm.getEmail(), "Account Verification", "Welcome to BLACK_FOOD, Please click on the link below to verify this account!,"+"\nhttp://localhost:8080/verify/"+userId);
        mailService.sendEmail(mail);
        return new ResponseEntity<>(new ResponMessage("Signup Success!"), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User userLogin = userService.findByUsername(userPrinciple.getUsername()).get();
        Long checkId = userLogin.getStatus().getId();
        if (checkId == 1) {
            return new ResponseEntity<>(new ResponMessage("Need to verification account, Please check your email!"),HttpStatus.OK);
        }
        else if (checkId == 2){
            String token = jwtProvider.createToken(authentication);
            return ResponseEntity.ok(new JwtResponse(userPrinciple.getId(),token, userPrinciple.getName(), userPrinciple.getAvatar(), userPrinciple.getAuthorities()));
        }
        else {
            return new ResponseEntity<>(new ResponMessage("Your account was blocked!"),HttpStatus.OK);
        }
    }
}
