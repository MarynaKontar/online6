package ua.goit.online6.mem.configuration;

import java.security.AuthProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Main entry point to all configuration of spring security.
 *
 * @author Andrey Minov
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private static final List<UserDetails> USERS;

  static {
    USERS = new ArrayList<>();
    USERS.add(User.withUsername("user").password("user").roles("user").build());
  }


  // By extention of WebSecurityConfigurerAdapter you can configure Spring Security
  // It called adapter because it 'adapts' interface WebSecurityConfigurer with defaults for all the methods
  // and default beans used

  // Minimum configuration need to run Spring security is configure UserDetailService
  // It became bridge between users from external storage and Spring users.
  @Bean
  public UserDetailsService userDetailsService(BCryptPasswordEncoder encoder) {
    return new MapUserDetailedService(USERS, encoder);
  }

  // From this now on we will get form login and XSS, CRSF protection and all other cool stuff.
  // Every kind of authentication will pass separate filter for checking.
  // List of all of them located at here http://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/htmlsingle/#filter-stack
  //
  // For example for form login responsible UsernamePasswordAuthenticationFilter
  // For basic - BasicAuthenticationFilter
  //
  // Inside this filters from HttpRequest is create Authentication object and pass to AuthenticationManager for verification.
  // AuthenticationManager will use list of available AuthenticationProvider objects to authentication users.
  //
  // So N types of authentication maps to M AuthenticationProvider-s with authenticate requests in 1 AuthenticationManager.
  // In case login form will be DaoAuthenticationProvider and PlaintextPasswordEncoder mapping passwords 1 by 1.
  // If password requirements match then it will successfully authenticate.
  //
  // We can change and implement our own AuthenticationProvider.
  // We can use encrypted password which matched hashes.
  //
  // password ~ '123456'
  // hash = sha256(password) // sha256(123456)
  // hash = sha256('xxxabc' + sha256('xxxabc' + password))
  // salt = 'xxxabc'
  //
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

//  @Bean
//  public AuthenticationProvider newProvider() {
//     return new AuthenticationProvider() {
//       @Override
//       public Authentication authenticate(
//           Authentication authentication) throws AuthenticationException {
//         return null;
//       }
//
//       @Override
//       public boolean supports(Class<?> authentication) {
//         return false;
//       }
//     };
//  }

  private static class MapUserDetailedService implements UserDetailsService {
    private Map<String, UserDetails> users;


    private MapUserDetailedService(List<UserDetails> userDetails, BCryptPasswordEncoder encoder) {
      this.users = new ConcurrentHashMap<>();
      userDetails.forEach(v -> users.put(v.getUsername(),
          User.withUsername(v.getUsername()).password(encoder.encode(v.getPassword()))
              .authorities(new ArrayList<>(v.getAuthorities())).build()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return Optional.ofNullable(users.get(username)).orElseThrow(
          () -> new UsernameNotFoundException("User " + username + " not found!"));
    }
  }
}
