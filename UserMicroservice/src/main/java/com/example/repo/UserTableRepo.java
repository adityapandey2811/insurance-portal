package com.example.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.User;

@Repository
public interface UserTableRepo extends CrudRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    User findByUsernameAndNickName(String username, String nickName);
}
