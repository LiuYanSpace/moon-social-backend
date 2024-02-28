package com.tothemoon.common.repository;

import com.tothemoon.common.entity.GroupUser;
import com.tothemoon.common.entity.GroupUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author birdyyoung
 */
@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, GroupUserId> {
    List<GroupUser> findByUserId(long userId);

}
