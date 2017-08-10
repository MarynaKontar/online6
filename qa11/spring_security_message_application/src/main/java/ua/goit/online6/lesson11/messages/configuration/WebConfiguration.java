package ua.goit.online6.lesson11.messages.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring MVC configuration used to configure all kind of resources.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.goit.online6.lesson11.messages.resources")
public class WebConfiguration extends WebMvcConfigurerAdapter {

}
