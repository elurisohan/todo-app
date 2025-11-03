package com.tracknote;

import com.tracknote.dao.UserRepository;
import com.tracknote.exception.UserNotFoundException;
import com.tracknote.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    /*overriding loadUserByUsername is mandatory to integrate custom user data with Spring Security's authentication system, enabling Spring to verify credentials and manage authorization. It provides a standard contract for loading user details instead of inventing a new method or interfac */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user =userRepository.findByUsername(username).
                orElseThrow(()->new UserNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}
