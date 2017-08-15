package ua.goit.online6.authorization.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Configuration for MVC.
 *
 * @author Andrey Minov
 */
@Configuration
@EnableWebSecurity
@ComponentScan("ua.goit.online6.authorization.endpoints")
public class MvcConfiguration extends WebMvcConfigurerAdapter {
}
