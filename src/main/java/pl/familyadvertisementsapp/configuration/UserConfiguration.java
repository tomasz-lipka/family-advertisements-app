package pl.familyadvertisementsapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import pl.familyadvertisementsapp.model.UserDto;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class UserConfiguration {
    //TODO auto create users table in sql if not exist

    @Autowired
    private DataSource dataSource;

//    @Bean
//    public UserDetails createUserDetails(UserDto userDto) {
//        return User.builder()
//                .username(userDto.getLogin())
//                //TODO encode password
//                .password("{noop}" + userDto.getPassword())
//                //TODO not hardcode role
//                .roles("USER")
//                .build();
//    }

//    @Bean
//    public UserDetailsManager userDetailsManager(UserDetails userDetails) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
////        jdbcUserDetailsManager.createUser(userDetails);
//        return jdbcUserDetailsManager;
//    }
}
