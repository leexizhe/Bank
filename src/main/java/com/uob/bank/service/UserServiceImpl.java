package com.uob.bank.service;

import com.uob.bank.dto.UserRegistrationDto;
import com.uob.bank.model.Role;
import com.uob.bank.model.Transaction;
import com.uob.bank.model.User;
import com.uob.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public String save(UserRegistrationDto userRegistrationDto) {
        if (Objects.isNull(userRepository.findByEmail(userRegistrationDto.getEmail()))) {
            User user = new User(userRegistrationDto.getFirstName(),
                    userRegistrationDto.getLastName(),
                    userRegistrationDto.getEmail(),
                    passwordEncoder.encode(userRegistrationDto.getPassword()),
                    userRegistrationDto.getContactNumber(),
                    userRegistrationDto.getAddress(),
                    userRegistrationDto.getNomineeDetails(),
                    Arrays.asList(new Role("ROLE_USER")),
                    Arrays.asList(new Transaction(500, LocalDateTime.now(), Transaction.TransactionType.DEPOSIT, Transaction.AccountType.SAVING)));
            userRepository.save(user);
            return "redirect:/registration?success";
        } else {
            return "redirect:/registration?failure";
        }
    }


    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String updatePassword(User user, String oldPassword, String newPassword) {
        System.out.println(user.getPassword() + "helloworld");
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return "redirect:/change-password?failureold";
        } else if (oldPassword.equals(newPassword)) {
            return "redirect:/change-password?failurenew";
        } else {
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return "redirect:/change-password?success";
        }
    }

    @Override
    public String updateProfile(UserRegistrationDto userRegistrationDto, User user) {
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setContactNumber(userRegistrationDto.getContactNumber());
        user.setAddress(userRegistrationDto.getAddress());
        user.setNomineeDetails(userRegistrationDto.getNomineeDetails());
        userRepository.save(user);
        return "redirect:/change-profile?success";
    }

    @Override
    public String deleteAccount(String checkPassword, User user) {
        if (passwordEncoder.matches(checkPassword, user.getPassword())) {
            userRepository.deleteById(user.getId());
            return "redirect:/login?delete";
        } else {
            return "redirect:/delete-account?failure";
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
