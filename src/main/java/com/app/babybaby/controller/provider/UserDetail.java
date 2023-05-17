package com.app.babybaby.controller.provider;

import com.app.babybaby.type.MemberType;
import com.app.babybaby.type.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Component
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetail implements UserDetails {

    private Long id;
    private String memberEmail;
    private String memberPassword;
    private Role memberRole;
    private MemberType memberType;
    private LocalDateTime memberRegisterDate;
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public UserDetail(Long id, String memberEmail, String memberPassword, Role memberRole, MemberType memberType, LocalDateTime memberRegisterDate, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberRole = memberRole;
        this.memberType = memberType;
        this.memberRegisterDate = memberRegisterDate;
        this.authorities = AuthorityUtils.createAuthorityList(memberRole.getSecurityRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(Role.GENERAL.getSecurityRole()), new SimpleGrantedAuthority(Role.ADMIN.getSecurityRole()), new SimpleGrantedAuthority(Role.COMPANY.getSecurityRole()));
    }

    @Override
    public String getPassword() {
        return memberPassword;
    }

    @Override
    public String getUsername() {
        return memberEmail;
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
