package com.example.demo.controllers;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserEntity;
import com.example.demo.models.request.LoginData;
import com.example.demo.models.response.LoginResponse;
import com.example.demo.models.request.RegisterData;
import com.example.demo.models.ResponseObject;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> authenticateUser(@Validated @RequestBody LoginData loginData) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginData.getUsername(), loginData.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        System.out.println(roles);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "User signed-in successfully!.", new LoginResponse(userPrincipal, token))
        );
    }

    @PostMapping("/register")
    ResponseEntity<ResponseObject> register(@Validated @RequestBody RegisterData registerData){
        if (userRepository.existsByUsername(registerData.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseObject("Failed", "This username already exist", " "));
        }
        if (!registerData.getPassword().equals(registerData.getRePassword())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseObject("Failed", "Password repeat incorrect", " "));
        }
        int id = (int) new Date().getTime();
        UserEntity user = new UserEntity(id, registerData.getUsername(),
                passwordEncoder.encode(registerData.getPassword()),
                "Active");
        System.out.println(user);
        Set<Integer> strRoles = registerData.getRoles();
        Set<Role> roles = new HashSet<>();
        System.out.println(strRoles);

        if (strRoles == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Failed", "Missing role", "")
            );
        } else {
            strRoles.forEach(role -> {
                Role selectRole = roleRepository.findById(role).orElse(null);
                if (selectRole != null) {
                    roles.add(selectRole);
                }
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElse(null);
//                        roles.add(adminRole);
//                        break;
//                    case "factory":
//                        Role factoryRole = roleRepository.findByName("ROLE_FACTORY").orElse(null);
//                        roles.add(factoryRole);
//                        break;
//                    case "service":
//                        Role serviceRole = roleRepository.findByName(ERole.ROLE_SERVICE).orElse(null);
//                        roles.add(serviceRole);
//                        break;
//                    case "distribution":
//                        Role distributionRole = roleRepository.findByName(ERole.ROLE_DISTRIBUTION).orElse(null);
//                        roles.add(distributionRole);
//                        break;
//                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Register successfully", "")
        );
    }
}
