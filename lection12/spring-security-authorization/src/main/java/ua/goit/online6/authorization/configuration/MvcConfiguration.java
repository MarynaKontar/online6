package ua.goit.online6.authorization.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configuration for MVC.
 *
 * @author Andrey Minov
 */
@Configuration
@EnableWebSecurity
@ComponentScan("ua.goit.online6.authorization.endpoints")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    super.addResourceHandlers(registry);
  }

  //
  // "view" -> new View("/PATH/TO/view")
  //
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    // mean we will work with JPS
    resolver.setViewClass(JstlView.class);
    resolver.setPrefix("/WEB-INF/jps/");
    resolver.setSuffix(".jsp");
    // Give me view name 'users' -> JstlView(/WEB-INF/jps/ + users + .jsp)
    return resolver;
  }
}
