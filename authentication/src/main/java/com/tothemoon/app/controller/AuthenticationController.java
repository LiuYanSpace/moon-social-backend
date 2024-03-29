package com.tothemoon.app.controller;

import com.tothemoon.app.dto.*;
import com.tothemoon.app.service.AuthenticationService;
import com.tothemoon.app.service.UserService;
import com.tothemoon.common.config.security.JwtUtils;
import com.tothemoon.common.config.security.UserDetailsImpl;
import com.bird.enums.Role;
import com.bird.exception.BadRequestException;
import com.bird.exception.ErrorReasonCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AuthenticationService authService;
    private final UserService userService;
    private final PasswordEncoder encoder;
    @PostMapping("/login")
    public ResponseEntity<LoginSuccessDTO> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
        Authentication authentication = null;
        try        {

            String identification = loginRequest.getIdentification();
            String password =  loginRequest.getPassword();
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(identification,password));

            log.info(loginRequest.getIdentification() + " successfully login");
        } catch (AuthenticationException e) {
            log.info(loginRequest.getIdentification() + " failed to login");
            throw new BadRequestException(ErrorReasonCode.Invalid_Username_Password);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        // TODO create an access_token record
        userService.createAccessToken(jwt);


        return ResponseEntity.ok(new LoginSuccessDTO(
                userDetails.getId(),
                jwt, userDetails.getNickName(),
                userDetails.getEmail(),
                roles,
                userDetails.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        userService.registerNewUser(registerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/forgotPassword")
    @ResponseStatus(value = HttpStatus.OK)
    public void forgetPassword(@Valid @RequestBody ForgotPasswordDTO forgotPasswordRequest) {
        authService.forgotPassword(forgotPasswordRequest.getEmail());
    }

    @PostMapping("/resetPassword")
    @ResponseStatus(value = HttpStatus.OK)
    public void resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordRequest) {
        authService.resetPassword(resetPasswordRequest.getResetKey(), resetPasswordRequest.getPassword());
    }
}
