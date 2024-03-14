package com.tothemoon.common.config.security;

import com.tothemoon.common.entity.GroupUser;
import com.tothemoon.common.entity.User;
import com.tothemoon.common.repository.GroupUserRepository;
import com.tothemoon.common.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final GroupUserRepository groupUserRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + usernameOrEmail));
        List<GroupUser> groups = groupUserRepository.findByUserId(user.getId());
        if (groups.isEmpty()) {
            throw new UsernameNotFoundException("User No permission: " + usernameOrEmail);
        }
        return new UserDetailsImpl(user, groups);
    }

}