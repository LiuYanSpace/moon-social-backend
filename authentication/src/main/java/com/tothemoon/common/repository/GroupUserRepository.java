package com.tothemoon.common.repository;

import com.tothemoon.common.entity.GroupUser;
import com.tothemoon.common.entity.GroupUserId;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author birdyyoung
 */
@Repository
public interface GroupUserRepository extends ListCrudRepository<GroupUser, GroupUserId> {
    List<GroupUser> findByUserId(long userId);

}
