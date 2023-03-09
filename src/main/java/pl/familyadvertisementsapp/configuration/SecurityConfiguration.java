package pl.familyadvertisementsapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/styles/**","/register").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll()
                .and().logout()
                .and().build();
    }

    @Autowired
    private DataSource dataSource;

    //TODO create UserConfig class
    @Bean
    public UserDetailsManager userDetailsManager() throws SQLException {
        //TODO auto create users table
        //https://www.javainuse.com/spring/boot_security_jdbc_authentication
        UserDetails user = User.builder()
                .username("asd2")
                .password("{noop}asd")
                .roles("USER")
                .build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        //jdbcUserDetailsManager.createUser(user);
        return jdbcUserDetailsManager;
    }
}
