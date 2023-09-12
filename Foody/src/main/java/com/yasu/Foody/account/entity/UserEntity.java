package com.yasu.Foody.account.entity;

import com.yasu.Foody.account.entity.roles.ERole;
import com.yasu.Foody.account.entity.roles.Role;
import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Document(collection="users")
@Data
@EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    private Long id;//idleri UUID yap
    private String userName;
    private String email;
    private String password;
    private String cellphone;
    private Boolean enabled;
    private String userType;
    private List<ERole> roles;

    /*
    public void setUserRoles(Set<Role> userRoles) {
        this.roles = userRoles;
    }
    public Set<Role> getAuthorities() {
        return this.roles;
    }

     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(
                authority -> new SimpleGrantedAuthority(authority.name())
        ).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled=true;
    }






}
