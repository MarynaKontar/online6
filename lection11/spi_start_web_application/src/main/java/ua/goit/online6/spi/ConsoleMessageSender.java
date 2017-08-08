package ua.goit.online6.spi;

/**
 * Message sender to console!
 */
public class ConsoleMessageSender implements MessageSender {
  @Override
  public void sendMessage(String message) {
    System.out.println("Message: " + message);
  }
}
