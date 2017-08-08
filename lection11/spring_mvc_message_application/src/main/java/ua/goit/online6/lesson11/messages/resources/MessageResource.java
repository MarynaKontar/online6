package ua.goit.online6.lesson11.messages.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Message sending endpoint.
 */
@RestController
@RequestMapping("/messages")
public class MessageResource {

  @PostMapping
  @RequestMapping("/send")
  public ResponseEntity<String> postMessage(String message) {
    System.out.println("Message is received: " + message);
    return ResponseEntity.ok("OK");
  }
}
