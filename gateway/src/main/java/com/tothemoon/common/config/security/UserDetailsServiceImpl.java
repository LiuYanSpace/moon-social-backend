package com.tothemoon.common.config.security;

import com.tothemoon.common.entity.GroupUser;
import com.tothemoon.common.entity.User;
import com.tothemoon.common.repository.GroupUserRepository;
import com.tothemoon.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final GroupUserRepository groupUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + usernameOrEmail));
        List<GroupUser> groups = groupUserRepository.findByUserId(user.getId());
        if (groups.isEmpty()) {
            throw new UsernameNotFoundException("User No permission: " + usernameOrEmail);
        }
        return new UserDetailsImpl(user, groups);
    }

}