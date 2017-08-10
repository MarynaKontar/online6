package ua.goit.online6.lesson11.messages.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Spring security configuration with Basic login form.
 *
 * @author Andrey Minov
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

  // We must define way how we know informations about user.
  // UserDetailsService simply loads all information about user by username.
  @Bean
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("test").password("test").roles("USER").build());
    return manager;
  }

  // Main entry for configuration of the security.
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // This means that all request must be authenticated by form login and http basic (for case of post message).
    http.authorizeRequests()
            .anyRequest()
            .authenticated()
        .and()
            .formLogin()
        .and()
            .httpBasic()
        .and()
            .csrf()
            .disable();
  }
}
