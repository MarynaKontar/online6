package ua.goit.online6.arithmetic;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Test for message processing.
 *
 * @author Andrey Minov
 */
@RunWith(MockitoJUnitRunner.class)
public class MessagesTest {

  @Mock
  private MessageSender messageSender;

  @Test
  public void testVoidMethod() {
    doAnswer(i -> null).when(messageSender).send("message", "address");
    messageSender.send("message", "address");
    verify(messageSender, times(1)).send("message", "address");
  }

  @Test
  public void testContentVoidMethod() {
    doAnswer(i -> {
      String message = i.getArgument(0);
      assertEquals("message", message);
      return null;
    }).when(messageSender).send("message", "address");
    messageSender.send("message", "address");
    verify(messageSender, times(1)).send("message", "address");
  }

  @Test
  public void testVoidAny() {
    doAnswer(i -> null).when(messageSender).send(eq("message"), anyString());
    messageSender.send("message", "address1");
    messageSender.send("message", "address2");
    verify(messageSender, times(2)).send(eq("message"), any());
    verify(messageSender, times(1)).send("message", "address1");
    verify(messageSender, times(1)).send("message", "address2");
  }

  @Test
  public void testSpy() {
    MessageSenderImpl sender = new MessageSenderImpl();
    sender = spy(sender);
    doAnswer(i -> null)
        .when(sender)
        .send("test", "test");

    sender.send("test", "test");
  }

  public static class MessageSenderImpl implements MessageSender {
    @Override
    public void send(String message, String address) {
      System.out.println("Send " + message +
                         " to " + address);
    }
  }

  public interface MessageSender {
    void send(String message, String address);
  }
}
