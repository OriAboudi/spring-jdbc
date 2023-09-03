package com.amigoscode.authentication;

import com.amigoscode.user.Role;
import com.amigoscode.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.w3c.dom.ranges.RangeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationDataAccessService implements UserDetailsService {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public int register(RegisterDTO registerDTO) {

        String existingUserQuery= """
                SELECT COUNT(*) 
                FROM jdbc.public.users
                WHERE
                email=?
                """;
        int existingUserCount = jdbcTemplate.queryForObject(
                existingUserQuery,Integer.class,registerDTO.email()
        );
        if(existingUserCount>0){
            throw new RuntimeException("User already exists");
        }
        var sql = """
                INSERT INTO jdbc.public.users
                (firstname, lastname, password, email, creatat,role)
                VALUES (?,?,?,?,?,?)
                """;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDate.now().format(formatter);

       return jdbcTemplate.update(sql,
                registerDTO.firstname(),
                registerDTO.lastname(),
                passwordEncoder.encode(registerDTO.password()),
                registerDTO.email(),
                formattedDate,
                Role.USER.name()

        );

    }

    public User authenticate(AuthenticateDTO authenticateDTO) {

       var user= loadUserByUsername(authenticateDTO.email());
       if(user== null || !passwordEncoder.matches(authenticateDTO.password(), user.getPassword())){

           throw new RangeException((short) 404, "Invalid Email Or Password");
       }

       return user;
    }

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        var sql = """
                SELECT *
                FROM jdbc.public.users
                WHERE email=?
                """;

       return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
           User u = new User();
           u.setFirstname(rs.getString("firstname"));
           u.setLastname(rs.getString("lastname"));
           u.setPassword(rs.getString("password"));
           u.setEmail(rs.getString("email"));
           u.setCrateAt(rs.getString("creatAt"));
           u.setRoll(rs.getString("role"));
           return u;
        }, email);
    }


}
