package com.amigoscode.authentication;

import com.amigoscode.jwt.JwtService;
import com.amigoscode.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationDataAccessService authenticationDataAccessService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterDTO registerDTO) {
        int userResult = authenticationDataAccessService.register(registerDTO);
        if (userResult>0) {
            var user  = authenticationDataAccessService.loadUserByUsername(registerDTO.email());
            return new RegisterResponse(
                    201,
                    "User create successfully",
                    jwtService.generateToken(new HashMap<>(),user)

            );
        } else {
            throw new IllegalArgumentException("Opp...");
        }

    }
    public AuthenticationResponse authenticate
            (AuthenticateDTO authenticateDTO) {
        var user =
                authenticationDataAccessService
                        .authenticate(authenticateDTO);
        if (user != null) {
            String jwt = jwtService.generateToken(user);
            return new AuthenticationResponse(jwt);

        }else {
            throw new IllegalArgumentException("Oops...");
        }
    }
}
