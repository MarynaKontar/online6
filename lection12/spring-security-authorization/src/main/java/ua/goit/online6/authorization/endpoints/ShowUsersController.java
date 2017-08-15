package ua.goit.online6.authorization.endpoints;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.online6.lesson11.jpa.dao.UsersService;
import ua.goit.online6.lesson11.jpa.entity.User;

@Controller
@RequestMapping("/user")
public class ShowUsersController {

  @Autowired
  private UsersService usersService;

  @RequestMapping(method = RequestMethod.GET, value = "/show")
  public ModelAndView showUsers() {
    List<String> usernames =
        usersService.findAll()
                    .stream()
                    .map(User::getUsername)
                    .collect(Collectors.toList());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("users");
    modelAndView.addObject("users", usernames);
    return modelAndView;
  }
}
