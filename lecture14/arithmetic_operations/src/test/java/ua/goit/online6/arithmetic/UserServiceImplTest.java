package ua.goit.online6.arithmetic;


import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.goit.online6.authorization.services.UserDetailedServiceImpl;
import ua.goit.online6.lesson11.jpa.dao.UsersDao;
import ua.goit.online6.lesson11.jpa.dao.UsersService;
import ua.goit.online6.lesson11.jpa.entity.User;

public class UserServiceImplTest {

  private UserDetailsService userDetailsService;
  private UsersService usersService;
  private UsersDao usersDao;
  private User user;

  @Before
  public void setUp() {
    // Mocks.
    // Stubs and Mocks
    // NOTE: final class and final methods not allowed
    usersDao = mock(UsersDao.class);
    user = mock(User.class);
    usersService = new UsersService(usersDao);
    userDetailsService = new UserDetailedServiceImpl(usersService);
  }

  @Test(expected = UsernameNotFoundException.class)
  public void testUserNotExists() {
    when(usersDao.findOne("B")).thenReturn(null);
    userDetailsService.loadUserByUsername("B");
  }

  @Test
  public void testCredentials() {
    when(user.getUsername()).thenReturn("username");
    when(user.getPassword()).thenReturn("password");
    when(user.isAdministrator()).thenReturn(false);
    when(usersDao.findOne("username")).thenReturn(user);

    UserDetails userDetails =
        userDetailsService.loadUserByUsername("username");
    assertEquals("username", userDetails.getUsername());
    assertEquals("password", userDetails.getPassword());
    assertTrue(userDetails.isAccountNonExpired());

    Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
    assertEquals(
        singletonList(new SimpleGrantedAuthority("ROLE_USER")),
           authorities);
  }

  @Test
  public void testFindOne() {
    when(usersDao.findOne("A")).thenReturn(user);
    assertEquals(user, usersService.findOne("A"));
    verify(usersDao, times(1)).findOne("A");
  }

  @Test
  public void testSave() {
    usersService.save(user);
    verify(usersDao, times(1)).save(user);
  }

}
