package ua.goit.online6.spi;

import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;

/**
 * This is entry point for application! Classes which implements ServletContainerInitializer
 * will be started by web container by searching them into SPI.
 * <p>
 * This is folder under META-INF/services. There must be located file
 * named same as interface (javax.servlet.ServletContainerInitializer in our case) and
 * inside file must be single line with implementation class (ua.goit.online6.spi.WebApplicationStarter)
 *
 * @author Andrey Minov
 */
// You can request implementation of some interfaces
@HandlesTypes(MessageSender.class)
public class WebApplicationStarter implements ServletContainerInitializer {

  @Override
  public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
    Class<?> messageSenderClazz = c.iterator().next();
    // Here we can use implementations, for example for use IoC or create instances via reflection.
    // You also register dynamic servlets here.
    MessageSender messageSender = newInstance(messageSenderClazz);
    MessageWriterServlet messageWriterServlet = new MessageWriterServlet(messageSender);
    ServletRegistration.Dynamic registration =
        ctx.addServlet("messageSender", messageWriterServlet);
    registration.setLoadOnStartup(1);
    registration.addMapping("/messages/send");
    // Now lets add filter for checking parameters.
    //ctx.addFilter("paramsCheck", new ParamsCheckFilter())
    //   .addMappingForUrlPatterns(null, true, "/messages/send");
    // And add also BasicAuthFilter
    //ctx.addFilter("basicAuth", new BasicAuthFilter())
    //   .addMappingForUrlPatterns(null, false, "/*");
  }

  @SuppressWarnings("unchecked")
  private <T> T newInstance(Class<?> clazz) throws ServletException {
    try {
      return (T) clazz.newInstance();
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
