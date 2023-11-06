package by.it_akademy.fitness.security.configuration.config;

import by.it_akademy.fitness.storage.api.IUserStorage;
import by.it_akademy.fitness.storage.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl {

    private final IUserStorage userStorage;

    public UserDetailsServiceImpl(IUserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Bean
    public UserDetailsService userDetailsService() throws UsernameNotFoundException {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
                //In this method (loadUserByUsername) we load user by userName
                //the one that we will use the JWT authentication filter
                //in this case we are not fetching user from database
                //we use a static list
                /**/
                //return userDao.findByUsername(email);
                User user = userStorage.findByLogin(login);
                return new CustomUserDetails(user);
            }
        };
    }
}
