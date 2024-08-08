package com.abhishek.ServiceBookingSystem.controller;

import com.abhishek.ServiceBookingSystem.dto.AuthenticationRequest;
import com.abhishek.ServiceBookingSystem.dto.SignupRequestDTO;
import com.abhishek.ServiceBookingSystem.dto.UserDto;
import com.abhishek.ServiceBookingSystem.entity.User;
import com.abhishek.ServiceBookingSystem.repository.UserRepository;
import com.abhishek.ServiceBookingSystem.services.authentication.AuthService;
import com.abhishek.ServiceBookingSystem.services.jwt.UserDetailsServiceImpl;
import com.abhishek.ServiceBookingSystem.util.Jwtutil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Jwtutil jwtutil;

    @Autowired
    AuthenticationManager authenticationManager;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/client/signup")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDTO signupRequestDTO) {
        if (authService.presentByEmail(signupRequestDTO.getEmail())) {
            return new ResponseEntity<>("A client already exists with this email", HttpStatus.OK);
        }

        UserDto createdUser = authService.signUpClient(signupRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/company/signup")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDTO signupRequestDTO) {
        if (authService.presentByEmail(signupRequestDTO.getEmail())) {
            return new ResponseEntity<>("A company already exists with this email", HttpStatus.OK);
        }

        UserDto createdUser = authService.signUpCompany(signupRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public void createAuthToken(@RequestBody AuthenticationRequest authenticationRequest,
                                HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtutil.generateToken(userDetails.getUsername());
        User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());
        response.getWriter().write(new JSONObject()
                .put("userId", user.getId())
                .put("role", user.getRole())
                .toString()
        );

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization," +
                " X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
    }

}
