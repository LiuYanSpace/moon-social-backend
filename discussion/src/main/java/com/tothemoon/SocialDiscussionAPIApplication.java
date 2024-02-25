package com.tothemoon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
@SpringBootApplication
@Slf4j
//@EnableFeignClients(basePackages="com.bird.app.integratedsupplier")
public class SocialDiscussionAPIApplication
{
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(SocialDiscussionAPIApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("\n-----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                "http",
                env.getProperty("server.port"),
                "http",
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }
}
