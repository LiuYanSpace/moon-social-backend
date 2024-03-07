package com.tothemoon.app.feign.client;


import com.tothemoon.common.entity.User;

public interface ExternalAuthClient {
    User getUserByUserId(long userId);

}
