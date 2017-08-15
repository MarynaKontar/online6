package ua.goit.online6.mem;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import ua.goit.online6.mem.configuration.SecurityConfiguration;

/**
 * This is entry class for initilizing String WEB Security
 * by registering delegation filter.
 *
 * @author Andrey Minov
 */
public class WebSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
  // Simply Spring will register DelegatingFilterProxy that will handle all connections.
  public WebSecurityInitializer() {
    super(SecurityConfiguration.class);
  }
}
