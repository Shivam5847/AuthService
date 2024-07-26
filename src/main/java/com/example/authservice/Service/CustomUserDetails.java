package com.example.authservice.Service;

import com.example.authservice.Models.UserInfo;
import com.example.authservice.Models.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserInfo implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserInfo userInfo){
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

        for(UserRole role : userInfo.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getRole().toUpperCase()));
        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
