package com.amigoscode.user;

import java.util.Optional;

public interface UserDao {
   Optional<User> findByEmail(String email);
}
