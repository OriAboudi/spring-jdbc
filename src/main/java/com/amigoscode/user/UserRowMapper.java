package com.amigoscode.user;

import com.amigoscode.movie.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class UserRowMapper {

    public User mapRow(ResultSet resultSet ,int i) throws SQLException {
        return  new User(
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString(Role.USER.name()),
                null
        );
    }
}
