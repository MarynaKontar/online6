package ua.goit.online6.authorization;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ua.goit.online6.authorization.configuration.MvcConfiguration;
import ua.goit.online6.authorization.configuration.SecurityConfiguration;
import ua.goit.online6.lesson11.jpa.configuration.ModelConfiguration;

/**
 * First step always is to create initilizer for
 * registering of {@link org.springframework.web.servlet.DispatcherServlet}
 *
 * @author Andrey Minov
 */
public class MvcWebApplicationInitilizer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] {ModelConfiguration.class, SecurityConfiguration.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] {MvcConfiguration.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }
}
