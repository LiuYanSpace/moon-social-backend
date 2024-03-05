package com.tothemoon.common.repository;

import com.tothemoon.common.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository   extends JpaRepository<AccessToken, Long> {
}