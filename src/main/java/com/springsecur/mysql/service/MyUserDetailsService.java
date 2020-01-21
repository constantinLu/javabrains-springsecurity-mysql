package com.springsecur.mysql.service;

import com.springsecur.mysql.module.MyUserDetails;
import com.springsecur.mysql.module.UserEntity;
import com.springsecur.mysql.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEntity> optional = userRepository.findByUserName(s);

        optional.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + s));

        return optional.map(MyUserDetails::new).get();
    }
}
