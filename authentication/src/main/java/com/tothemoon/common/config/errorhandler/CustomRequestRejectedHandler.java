package com.tothemoon.common.config.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.RequestRejectedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomRequestRejectedHandler implements RequestRejectedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       RequestRejectedException requestRejectedException) throws IOException {

        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || "".equals(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }

        log.error("request_rejected: remote={}, user_agent={}, request_url={}",
                remoteAddr,
                request.getHeader(HttpHeaders.USER_AGENT),
                request.getRequestURL()
        );

        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
