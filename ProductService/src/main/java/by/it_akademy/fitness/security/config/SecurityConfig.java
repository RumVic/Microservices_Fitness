package by.it_akademy.fitness.security.config;

import by.it_akademy.fitness.security.costom.JwtAccessDeniedHandler;
import by.it_akademy.fitness.security.costom.JwtAuthenticationEntryPoint;
import by.it_akademy.fitness.security.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@EnableWebSecurity
public class SecurityConfig {


    public SecurityConfig(JwtAuthFilter jwtAuthFilter,
                          // TODO IUserStorage userStorage,
                          JwtAuthenticationEntryPoint authEntryPoint,
                          RestTemplate restTemplate, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        // TODO this.userStorage = userStorage;
        this.authEntryPoint = authEntryPoint;
        this.restTemplate = restTemplate;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    String login = "login";

    private final JwtAuthFilter jwtAuthFilter;

    // TODO private final IUserStorage userStorage;


    private final JwtAuthenticationEntryPoint authEntryPoint;

    private final RestTemplate restTemplate;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/registration/**").permitAll()
                .antMatchers("/**/activation/**").permitAll()
                .antMatchers("/**/login/**").permitAll()
                .antMatchers(HttpMethod.GET, "/**/product/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/**/users/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/**/me/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/**/users/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/**/recipe/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/**/profile/**").hasAnyAuthority("ROLE_USER")
                .antMatchers("/**/audit/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //if you authenticate your user the first time the
                //session will have always the authenticated state
                //and this is the behavior that we don't want to add
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                /*.exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                .and()*/
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler);


        //.addFilterBefore(new JwtRequestFilter(authenticationManager(), userDetailsService, new JWTHelper(), handlerExceptionResolver), UsernamePasswordAuthenticationFilter.class);
                /*.exceptionHandling().authenticationEntryPoint((request, response, authException) ->
                 response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        authException.getMessage()))*/
        ;
        //there we want to add filter before another filter(jwtAuthFilter before
        //there we told Spring , hey go ahead and use this filter before authentication the User
        //because (in jwtAuthFilter we are checking the JWT and if everything is fine what we do - we
        //set, or we update the context of the security context holder,so we want to execute
        //jwtAuthFilter before UsernamePasswordAuthenticationFilter

        return (SecurityFilterChain) http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        //this method that we want spring to use instead
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        //return null;
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
                //In this method (loadUserByUsername) we load user by userName
                //the one that we will use the JWT authentication filter
                //in this case we are not fetching user from database
                //we use a static list
                /**/
                //return userDao.findByUsername(email);

//              TODO   FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//              TODO           "http://localhost:8081/api/v1/fraud-check/{customerId}",
//              TODO           FraudCheckResponse.class,
//              TODO           customer.getId()
//              TODO   );

                UserDetails user = restTemplate.getForObject(
                        "http://localhost:8081/api/v1/fraud-check/{customerId}",
                        User.class);
                return user;
            }
        };
    }
}
