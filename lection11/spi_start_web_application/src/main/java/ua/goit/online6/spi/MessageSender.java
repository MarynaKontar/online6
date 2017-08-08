package ua.goit.online6.spi;

/**
 * Simple class for sending messages to the user.
 *
 * @author Andrey Minov
 */
public interface MessageSender {

  void sendMessage(String message);
}
