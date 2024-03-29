//package com.tothemoon.common.security;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.tothemoon.common.entity.GroupUser;
//import com.tothemoon.common.entity.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.io.Serial;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//
//public class UserDetailsImpl implements UserDetails {
//    @Serial
//    private static final long serialVersionUID = 1L;
//    private final Long id;
//    private final String email;
//    private final String nickName;
//    private final String userName;
//    @JsonIgnore
//    private final String password;
//
//    private final Collection<? extends GrantedAuthority> authorities;
//
//    public UserDetailsImpl(User user, List<GroupUser> groupUsers) {
//        this.id = user.getId();
//        this.email = user.getEmail();
//        this.password = user.getPassword();
//        this.nickName = user.getNickname();
//        this.userName = user.getUsername();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (GroupUser groupUser : groupUsers) {
//            authorities.add(new SimpleGrantedAuthority(groupUser.getGroup().getNameSingular()));
//        }
//        this.authorities = authorities;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public String getNickName() {
//        return nickName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }
//}