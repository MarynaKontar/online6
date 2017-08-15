package ua.goit.online6.authorization.endpoints;

import java.io.IOException;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.online6.lesson11.jpa.dao.UsersService;
import ua.goit.online6.lesson11.jpa.entity.User;

/**
 * This is rest endpoint to manipulate users.
 *
 * @author Andrey Minov
 */
@RestController
@RequestMapping("/user")
public class UserEndpoints {

  private final UsersService usersService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserEndpoints(UsersService usersService, PasswordEncoder passwordEncoder) {
    this.usersService = usersService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostConstruct
  public void initDefaultUsers() {
    User user = new User();
    user.setUsername("admin");
    user.setPassword(passwordEncoder.encode("admin"));
    user.setAdministrator(true);

    usersService.save(user);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/list")
  public String listAllUsers() {
    return usersService.findAll().stream().map(User::getUsername).collect(Collectors.joining(";"));
  }

  @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public void createUser(@RequestParam("username") @NotEmpty String username,
                                         @NotEmpty String password,
                                         @RequestParam("admin") boolean isAdmin,
                                         @RequestParam(required = false) String email,
                                         @RequestHeader(value = "x", required = false) String x,
                                         @CookieValue(value = "y", required = false) String y) throws IOException {
    User user = new User();
    user.setUsername(username);
    user.setAdministrator(isAdmin);
    user.setPassword(passwordEncoder.encode(password));
    user.setEmail(email);
    usersService.save(user);

    throw new IOException("IO test");
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleIOException(IOException ex) {
    return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
  }

//  @ExceptionHandler(Exception.class)
//  public ResponseEntity<String> handleException(Exception ex) {
//    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//  }

}
