package ua.goit.online6.authorization.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.online6.lesson11.jpa.dao.UsersService;
import ua.goit.online6.lesson11.jpa.entity.User;

/**
 * This is implementation for user detailed service which will use {@link ua.goit.online6.lesson11.jpa.dao.UsersDao} as
 * users repository.
 *
 * @author Andrey Minov
 */
@Service
public class UserDetailedServiceImpl implements UserDetailsService {

  private final UsersService usersDao;

  @Autowired
  public UserDetailedServiceImpl(UsersService usersDao) {
    this.usersDao = usersDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = usersDao.findOne(username);
    if (user == null) {
      throw new UsernameNotFoundException("User is not found!");
    }
    return new UserDetailsExt(user);
  }

  private static class UserDetailsExt implements UserDetails {
    private User user;
    private Collection<SimpleGrantedAuthority> grantedAuthorities;

    UserDetailsExt(User user) {
      this.user = user;
      this.grantedAuthorities = new ArrayList<>();
      if (user.isAdministrator()) {
        //ROLE_
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
      } else {
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
      }
    }

    @Override
    public String getUsername() {
      return user.getUsername();
    }

    @Override
    public String getPassword() {
      return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }

    public String getEmail() {
      return user.getEmail();
    }
  }
}
