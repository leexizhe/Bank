package com.uob.bank.service;

import com.uob.bank.dto.UserRegistrationDto;
import com.uob.bank.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    String save(UserRegistrationDto userRegistrationDto);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User getUserByEmail(String email);

    String updatePassword(User user, String oldPassword, String newPassword);

    String updateProfile(UserRegistrationDto userRegistrationDto, User user);

    String deleteAccount(String password, User user);
}
