package ru.skypro.homework.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repositories.UserRepository;

import java.util.function.Supplier;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Object user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User is not found"));
        return new MyUserDetails((User) user);
    }
}
