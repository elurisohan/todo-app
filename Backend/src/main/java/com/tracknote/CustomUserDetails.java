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

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());//The third argument is Collection<? extends GrantedAuthority> authorities.​You are passing an empty list of authorities, meaning “this user has no roles/permissions configured yet.”​. Later, when you add roles (like ROLE_USER, ROLE_ADMIN), you would replace new ArrayList<>() with a collection of GrantedAuthority objects built from your user’s roles
    }
}
// CustomUserDetails bridges our DB User entity with Spring Security's authentication system.
// - Spring Security doesn't know about our com.tracknote.model.User.
// - It only understands objects that implement UserDetails.
// - loadUserByUsername(String username) is the standard method Spring Security calls during login
//   to fetch user data from our database.
// - Here we:
//   1) Use UserRepository to find the User by username.
//   2) If not found, throw an exception.
//   3) Convert our User entity into Spring Security's built-in UserDetails implementation
//      (org.springframework.security.core.userdetails.User), passing username, password,
//      and authorities.
// - The returned UserDetails object is what Spring Security uses to authenticate and authorize
//   the user for incoming requests.
