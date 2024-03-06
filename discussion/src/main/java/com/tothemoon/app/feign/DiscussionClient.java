package com.tothemoon.app.feign;

import com.tothemoon.app.feign.client.ExternalAuthClient;
import com.tothemoon.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiscussionClient implements ExternalAuthClient {

//    private final OrderItOrderMapper orderItOrderMapper;
    private final DiscussionAuthFeignClient discussionAuthFeignClient;

    @Value("${feign.auth-service.token}")
    private String token;

    @Override
    public User getUserByUserId(long userId) {
       return discussionAuthFeignClient.getUserByUserId(userId).getBody();
    }



}
