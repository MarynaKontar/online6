package ua.goit.online6.mem.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is simple CRUD operation for get information of the users.
 *
 * @author Andrey Minov
 */
@WebServlet(name = "users", urlPatterns = "/user", loadOnStartup = 1)
public class UserService extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {
    resp.getOutputStream().println("<p>Some list of users<p>");
    resp.getOutputStream().flush();
  }
}
