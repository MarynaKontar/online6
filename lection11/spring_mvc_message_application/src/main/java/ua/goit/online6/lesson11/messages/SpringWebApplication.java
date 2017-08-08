package ua.goit.online6.lesson11.messages;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ua.goit.online6.lesson11.messages.configuration.WebConfiguration;

/**
 * Main entry point to Spring MVC application.
 *
 * @author Andrey Minov
 */
public class SpringWebApplication extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    // All other configuration classes goes here
    return new Class[0];
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // Web MVC configuration goes here
    return new Class[] {WebConfiguration.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }
}
