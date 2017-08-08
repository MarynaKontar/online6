package ua.goit.online6.spi;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * This filter will help us validate incoming parameters before making actual call
 * to application!
 *
 * @author Andrey Minov
 */
public class ParamsCheckFilter implements Filter {

  private static final String MESSAGE_PARAM = "message";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {

    // We will try to check is message is existed! Otherwise we will have to send 400 error!
    String message = request.getParameter(MESSAGE_PARAM);
    if (message == null || message.isEmpty()) {
      // ServletResponse is not HTTP response so we have to cast it.
      ((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    // In case all us ok we have to pass this request forward to the filter!
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
