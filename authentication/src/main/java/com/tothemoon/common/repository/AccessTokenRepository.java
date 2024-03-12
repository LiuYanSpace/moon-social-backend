package com.tothemoon.common.repository;

import com.tothemoon.common.entity.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository   extends CrudRepository<AccessToken, Long> {
}
