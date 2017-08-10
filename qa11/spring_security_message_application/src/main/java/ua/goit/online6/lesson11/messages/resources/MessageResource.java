package ua.goit.online6.lesson11.messages.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Message sending endpoint.
 */
@RestController
@RequestMapping("/messages")
public class MessageResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageResource.class);

  private Map<Object, Queue<String>> messages;

  public MessageResource() {
    this.messages = new ConcurrentHashMap<>();
  }

  @PostMapping
  @RequestMapping("/send")
  // /messages/send
  public ResponseEntity<String> postMessage(String message, Authentication user) {
    LOGGER.info("Message is received: {} from user {}", message, user.getPrincipal());
    messages.computeIfAbsent(user.getPrincipal(), k -> new ConcurrentLinkedQueue<>()).add(message);
    return ResponseEntity.ok("OK");
  }

  @GetMapping
  @RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  // /messages/get
  public ResponseEntity<List<String>> getMessage(Authentication user) {
    LOGGER.info("Messages are requested from user {}", user.getPrincipal());
    Queue<String> msgs = messages.get(user.getPrincipal());
    if (msgs == null || msgs.isEmpty()) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.ok(new ArrayList<>(msgs));
  }
}
