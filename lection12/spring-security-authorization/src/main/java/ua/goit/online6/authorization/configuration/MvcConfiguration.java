package ua.goit.online6.authorization.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configuration for MVC.
 *
 * @author Andrey Minov
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.goit.online6.authorization.endpoints")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // http://localhost:8080/jpeg/face.jpeg -> /WEB-INF/jpegs/face.jpeg
    registry.addResourceHandler("/jpeg/**").addResourceLocations("/WEB-INF/jpegs/");
    registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
    registry.addResourceHandler("/*.html").addResourceLocations("/WEB-INF/html/");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    // View -> RedirectView
    registry.addRedirectViewController("/", "/user/show");
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
