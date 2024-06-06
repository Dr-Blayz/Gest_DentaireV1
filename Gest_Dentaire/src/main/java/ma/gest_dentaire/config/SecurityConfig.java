package ma.gest_dentaire.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests

                                .requestMatchers("/Pages/**").hasAnyRole("Dentiste", "Secretaire")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();

        var admin = User.withDefaultPasswordEncoder()
                .username("Den1")
                .password("DenDen")
                .roles("Dentiste")
                .build();

        var nurse = User.withDefaultPasswordEncoder()
                .username("Sec1")
                .password("SecSec")
                .roles("Secretaire")
                .build();

        userDetailsManager.createUser(admin);
        userDetailsManager.createUser(nurse);

        return userDetailsManager;
    }
}