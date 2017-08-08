package ua.goit.online6.spi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet which can be used to post message into message sender.
 *
 * @author Andrey Minov
 */
public class MessageWriterServlet extends HttpServlet {

  private static final String MESSAGE_PARAM = "message";

  private MessageSender messageSender;

  public MessageWriterServlet(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  @Override
  protected void doPost(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
    // Get parameter from request.
    // Post it to message sender.
    String message = req.getParameter(MESSAGE_PARAM);
    messageSender.sendMessage(message);
  }
}
