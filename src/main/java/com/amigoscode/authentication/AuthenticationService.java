package com.amigoscode.authentication;

import com.amigoscode.jwt.JwtService;
import com.amigoscode.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationDataAccessService authenticationDataAccessService;
    private JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterDTO registerDTO) {
        int result = authenticationDataAccessService.register(registerDTO);
        if (result > 0) {
            return new RegisterResponse(
                    201,
                    "User create successfully"
            );
        } else {
            throw new IllegalArgumentException("Opp...");
        }

    }
    public AuthenticationResponse authenticate
            (AuthenticateDTO authenticateDTO) {
        UserDetails user =
                authenticationDataAccessService
                        .authenticate(authenticateDTO);
        if (user != null) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticateDTO.email(),
                            authenticateDTO.password()
                    ));
            return new AuthenticationResponse(jwtService.generateToken(user));
        }else {
            throw new IllegalArgumentException("Oops...");
        }



    }
}
