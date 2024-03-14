package com.tothemoon.common.repository;

import com.tothemoon.common.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdIsNot(String email, long id);

    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);
}
