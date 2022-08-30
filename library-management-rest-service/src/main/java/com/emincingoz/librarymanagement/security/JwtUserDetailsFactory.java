package com.emincingoz.librarymanagement.security;

import com.emincingoz.librarymanagement.domain.enums.UserRolesEnum;
import com.emincingoz.librarymanagement.domain.models.User;
import com.emincingoz.librarymanagement.domain.models.UserAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserDetailsFactory {

    public static JwtUserDetails create(User user) {
        return new JwtUserDetails(
                user.getId(),
                user.getUserName(),
                user.getFirstName() + ' ' + user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthority(user.getRoles()),
                user.isActivated(),
                null
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<UserRolesEnum> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toList());
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(List<UserAuthority> roleList) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        roleList.forEach(userRole -> authorityList.add(new SimpleGrantedAuthority(userRole.getAuthorityName().getName().name())));
        return authorityList;
    }
}
