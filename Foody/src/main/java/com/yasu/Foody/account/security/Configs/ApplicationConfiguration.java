package com.yasu.Foody.account.security.Configs;


import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.entity.roles.ERole;
import com.yasu.Foody.account.repository.UserRepository;
import com.yasu.Foody.account.security.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * <span style='color:white'>Step 2: Create the ApplicationConfiguration class</span>
 * <ul>
 *     <li>
 *         Inject the ReactiveUserRepository bean using constructor injection.
 *     </li>
 *     <li>
 *         Create the {@link PasswordEncoder PasswordEncoder} bean.
 *         <p>Use the {@link BCryptPasswordEncoder BCryptPasswordEncoder} class to create the PasswordEncoder.</p>
 *     </li>
 *     <li>
 *         Create the {@link ReactiveUserDetailsService ReactiveUserDetailsService} bean
 *         (it is equivalent to {@link UserDetailsService UserDetailsService} in blocking spring).
 *         <p>Map the User object to a {@link org.springframework.security.core.userdetails.User org.springframework.security.core.userdetails.User} object.</p>
 *     </li>
 *     <li>
 *         Insert some demo data in db using {@link CommandLineRunner CommandLineRunner}.
 *     </li>
 * </ul>
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final UserRepository reactiveUserRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Returns a ReactiveUserDetailsService bean that retrieves user details from the database.
     *
     * @return a ReactiveUserDetailsService bean
     */
    @Bean
    public ReactiveUserDetailsService reactiveUserDetailsService() {
        return username -> reactiveUserRepository.findByEmail(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                                user.getUsername(),
                                user.getPassword(),
                                user.getAuthorities()
                        )
                );
    }

    /**
     * Returns a CommandLineRunner bean that inserts demo users into the database.
     *
     * @return a CommandLineRunner bean
     */
    /*
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            UserEntity user = new UserEntity();
            user.setEmail("user");
            user.setPassword(passwordEncoder().encode("user"));
            user.setRoles(List.of(ERole.ROLE_USER));


            UserEntity admin = new UserEntity();
            admin.setEmail("admin");
            admin.setPassword(passwordEncoder().encode("admin"));

            admin.setRoles(List.of(ERole.ROLE_USER, ERole.ROLE_ADMIN));

            reactiveUserRepository.saveAll(List.of(user,admin)).blockLast();
        };
    }


     */
}