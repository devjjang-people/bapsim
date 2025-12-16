package com.devpeople.bapsim.global.security;

import com.devpeople.bapsim.domain.user.entity.User;
import com.devpeople.bapsim.domain.user.entity.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) { this.user = user; }

    public Long getUserId() { return user.getId(); }
    public String getEmail() { return user.getEmail(); }
    public String getRoleName() {
        return user.getRole().toString();
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + getRoleName()));
    }

    @Override public String getPassword() { return user.getPassword(); }
    @Override public String getUsername() { return user.getEmail(); }

    @Override public boolean isAccountNonLocked() {
        return user.getStatus() != UserStatus.WITHDRAWN;
    }

    @Override public boolean isEnabled() {
        return user.getStatus() == UserStatus.ACTIVE;
    }
}
