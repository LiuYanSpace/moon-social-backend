package com.tothemoon.app.service;

import com.tothemoon.app.dto.RegisterDTO;
import com.tothemoon.app.dto.UserDTO;
import com.tothemoon.common.config.SecurityUtil;
import com.bird.exception.BadRequestException;
import com.bird.exception.ConflictRequestException;
import com.bird.exception.ErrorReasonCode;
import com.bird.exception.NotFoundRequestException;
import com.tothemoon.common.entity.User;
import com.tothemoon.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void resetPassword(String resetKey, String password) {
        log.debug("Reset password triggered for resetKey {}", resetKey);

        if (Objects.isNull(resetKey) || resetKey.isEmpty()) {
            throw new BadRequestException(ErrorReasonCode.Invalid_Reset_Key);
        }

//        User member = userRepository.findByResetKey(resetKey).orElseThrow(() -> new BadRequestException(ErrorReasonCode.Invalid_Reset_Key));
//
//        member.setPassword(encoder.encode(password));
//        userRepository.save(member);
    }


    public void registerNewUser(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new ConflictRequestException(ErrorReasonCode.Duplicated_UserEmail);
        }
        User user = new User();
        user.setNickname(registerDTO.getNickName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        user.setInviteCode(registerDTO.getDoorKey());
        userRepository.save(user);
    }

    public User updateUser(UserDTO userDTO) {
        User preMember = getUser();
        preMember.setUsername(userDTO.getUsername());
        preMember.setBio(userDTO.getBio());
        preMember.setAvatarUrl(userDTO.getAvatarUrl());
        return userRepository.save(preMember);
    }

    public String getAndUpdateMemberProfileImage(String imageUrl) {
        User preUser= getUser();
        preUser.setAvatarUrl(imageUrl);
        userRepository.save(preUser);
        return preUser.getAvatarUrl();
    }

    public String getMemberProfileImage() {
        return getUser().getAvatarUrl();
    }

    public User getUser() {
        Long userId = SecurityUtil.getCurrentUserId();
        return getUserById(userId);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));

    }

}
