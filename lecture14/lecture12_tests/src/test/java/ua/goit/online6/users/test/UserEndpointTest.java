package ua.goit.online6.users.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.online6.authorization.configuration.MvcConfiguration;
import ua.goit.online6.authorization.configuration.SecurityConfiguration;
import ua.goit.online6.authorization.endpoints.UserEndpoints;
import ua.goit.online6.lesson11.jpa.dao.UsersService;
import ua.goit.online6.lesson11.jpa.entity.User;

/**
 * Tests for {@link UserEndpoints}.
 *
 * @author Andrey Minov
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
    MvcConfiguration.class,
    SecurityConfiguration.class,
    ApplicationTestModelConfiguration.class})
public class UserEndpointTest {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private UsersService usersService;

  private User user;

  @Before
  public void setUp() throws Exception {
    user = mock(User.class);
    when(user.getUsername()).thenReturn("A");
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }

  @Test
  public void testShowUsers() throws Exception {
    when(usersService.findAll()).thenReturn(Collections.singletonList(user));

    mvc.perform(get("/user/show").with(user("test").roles("ADMIN")))
       .andExpect(MockMvcResultMatchers.status().isOk())
       .andExpect(model().attribute("users", equalTo(Collections.singletonList("A"))))
       .andExpect(view().name("users"));
  }

  @Test
  public void testShowUser() throws Exception {
    when(usersService.findOne("A")).thenReturn(user);

    mvc.perform(get("/user/find/A").with(user("test").roles("ADMIN"))).andExpect(status().isOk())
       .andExpect(model().attribute("user", user))
       .andExpect(view().name("user"));
  }

  @Test
  public void logoutTest() throws Exception {
    mvc.perform(post("/logout").with(user("test").roles("ADMIN")))
       .andExpect(redirectedUrl("/login?logout"))
       .andExpect(status().isFound());
  }

}
