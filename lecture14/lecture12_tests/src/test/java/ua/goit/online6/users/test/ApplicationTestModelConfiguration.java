package ua.goit.online6.users.test;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.goit.online6.lesson11.jpa.dao.UsersService;

/**
 * Provide mocks for data layer.
 *
 * @author Andrey Minov
 */
@Configuration
public class ApplicationTestModelConfiguration {

  @Bean
  public UsersService usersService() {
    return mock(UsersService.class);
  }
}
