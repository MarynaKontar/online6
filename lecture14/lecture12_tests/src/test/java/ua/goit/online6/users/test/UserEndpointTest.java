package ua.goit.online6.users.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.online6.authorization.configuration.MvcConfiguration;
import ua.goit.online6.authorization.configuration.SecurityConfiguration;
import ua.goit.online6.authorization.endpoints.UserEndpoints;
import ua.goit.online6.lesson11.jpa.configuration.ModelConfiguration;

/**
 * Tests for {@link UserEndpoints}.
 *
 * @author Andrey Minov
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {})
public class UserEndpointTest {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testShowUsers() throws Exception {
     mvc.perform(get("/user/list")).andExpect(status().isOk());
  }

}
