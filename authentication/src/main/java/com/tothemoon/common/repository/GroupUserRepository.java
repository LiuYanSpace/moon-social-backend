package com.tothemoon.common.repository;

import com.tothemoon.common.entity.GroupUser;
import com.tothemoon.common.entity.GroupUserId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author birdyyoung
 */
@Repository
public interface GroupUserRepository extends CrudRepository<GroupUser, GroupUserId> {
    List<GroupUser> findByUserId(long userId);

}
