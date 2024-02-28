package com.tothemoon.common.repository;

import com.tothemoon.common.entity.Group;
import com.tothemoon.common.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdIsNot(String email, long id);

    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);


    @Query("SELECT gu.group FROM GroupUser gu WHERE gu.user = :user")
    List<Group> findGroupsByUser(@Param("user") User user);
}
