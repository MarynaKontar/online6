package ua.goit.online6.authorization.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration for Spring security.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("ua.goit.online6.authorization.services")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  // We will use BC password encoder and form login configuration.
  // Configure this all by configure method.
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Basic idea is next - create controller where:
    // 1. USER can check all users in the system.
    // 2. ADMIN can create, update or remove users.
    http.authorizeRequests().antMatchers("/user/list").hasAnyRole("USER", "ADMIN")
                            .antMatchers("/user/**").hasRole("ADMIN")
                            .anyRequest().denyAll()
        .and()
        .httpBasic();
  }
}
