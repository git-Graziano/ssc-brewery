package guru.sfg.brewery.bootstrap;

import guru.sfg.brewery.domain.security.Authority;
import guru.sfg.brewery.domain.security.User;
import guru.sfg.brewery.repositories.security.AuthorityRepository;
import guru.sfg.brewery.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String PASSWORD = "password";

    @Override
    public void run(String... args) throws Exception {
        loadSecurity();
    }

    private void loadSecurity() {

        if(authorityRepository.count() == 0) {

            PasswordEncoder bcrypt = new BCryptPasswordEncoder();

            Authority adminAuthority = authorityRepository.save(Authority.builder()
                    .role("ADMIN")
                    .build());
            Authority userAuthority = authorityRepository.save(Authority.builder()
                    .role("USER")
                    .build());
            Authority customerAuthority = authorityRepository.save(Authority.builder()
                    .role("CUSTOMER")
                    .build());
            // spring
            User springUser = User.builder()
                    .username("spring")
                    .password(bcrypt.encode("guru"))
                    .authority(adminAuthority)
                    .build();

            userRepository.save(springUser);

//            adminAuthority.getUsers().add(springUser);

            // user
            User user = User.builder()
                    .username("user")
                    .password(bcrypt.encode("password"))
                    .authority(userAuthority)
                    .build();
            userRepository.save(user);

  //          userAuthority.getUsers().add(user);

            // scott
            User scottUser = User.builder()
                    .username("scott")
                    .password(bcrypt.encode("tiger"))
                    .authority(customerAuthority)
                    .build();

            userRepository.save(scottUser);
//            customerAuthority.getUsers().add(scottUser);

            log.debug("Users loaded; " + userRepository.count());
        }
    }
}
