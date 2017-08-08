package ua.goit.online6.spi;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter which helps as use HTTP basic authentication.
 *
 * @author Andrey Minov
 */
public class BasicAuthFilter implements Filter {

  private static final String AUTHENTICATION_HEADER = "Authorization";
  private static final String BASIC_AUTH = "Basic";

  private Map<String, String> userPasswords;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    userPasswords = new ConcurrentHashMap<>();
    userPasswords.put("test", "test");
    userPasswords.put("John", "password");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    // In case basic authentication is not presented - we send 401 response!
    String header = httpServletRequest.getHeader(AUTHENTICATION_HEADER);
    if (header == null || header.isEmpty()) {
      httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    Scanner scanner = new Scanner(header);
    String type = scanner.hasNext() ? scanner.next() : null;
    // We now support only basic authorization.
    if (!BASIC_AUTH.equals(type)) {
      httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    // Assume we have correct spring.
    String decodedString = scanner.hasNext() ? scanner.next() : null;
    if (decodedString == null || decodedString.isEmpty()) {
      httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    Charset charset = Charset.forName("UTF-8");
    byte[] bytes = Base64.getDecoder().decode(decodedString.getBytes(charset));
    decodedString = new String(bytes, charset);
    String[] parts = decodedString.split(":");
    // Password string will be in way -> username:password.
    if (parts.length != 2) {
      httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    // Get stored password for the user.
    String storedPass = userPasswords.get(parts[0]);
    if (storedPass == null || !storedPass.equals(parts[1])) {
      httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    // If all is -> go forward by chain!
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
