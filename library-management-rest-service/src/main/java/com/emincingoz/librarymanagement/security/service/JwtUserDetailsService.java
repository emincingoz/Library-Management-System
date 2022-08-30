package com.emincingoz.librarymanagement.security.service;

import com.emincingoz.librarymanagement.domain.models.User;
import com.emincingoz.librarymanagement.repository.IUserRepository;
import com.emincingoz.librarymanagement.security.JwtUserDetailsFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    public JwtUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        return JwtUserDetailsFactory.create(user);
    }
    
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).get();
        return JwtUserDetailsFactory.create(user);
    }
}
