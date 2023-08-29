package com.amigoscode.authentication;
import com.amigoscode.user.Role;
import com.amigoscode.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationDataAccessService {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    public  int register(RegisterDTO registerDTO) {

        var sql = """
                   INSERT INTO jdbc.public.users
                   (firstname, lastname, password, email, creatat,role)\s
                   VALUES (?,?,?,?,?,?)
                   """;

       return jdbcTemplate.update(sql,
                registerDTO.firstname(),
                registerDTO.lastname(),
                registerDTO.email(),
                passwordEncoder.encode(registerDTO.password()),
                Role.USER.name(),
                LocalDate.now()
        );


    }

    public UserDetails authenticate(AuthenticateDTO authenticateDTO) {

        var sql = """
                  SELECT firstname, lastname, password, email, creatat,role
                  FROM jdbc.public.users
                  WHERE (email=?)
                  """;

        return jdbcTemplate.query(sql, (resultSet) -> {
            return new User(
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    null,
                    resultSet.getString(Role.USER.name()),
                    resultSet.getDate("createAt"));
        }, authenticateDTO.email());

    }
}
