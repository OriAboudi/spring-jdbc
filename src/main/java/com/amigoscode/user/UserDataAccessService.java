package com.amigoscode.user;

import com.amigoscode.movie.MovieRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserDataAccessService implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Optional<User>  findByEmail(String email) {
        var sql = """
                SELECT id, firstname,lastname,email,creatat,role
                FROM jdbc.public.users
                WHERE email = ?;
                """;

        return jdbcTemplate.query(sql,(resultSet,i)->{
            return new User(
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    null,
                    resultSet.getString(Role.USER.name()),
                    null

            );
        },email)
                .stream()
                .findFirst();

    }
}
