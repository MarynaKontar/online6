package ua.goit.online6.authorization.endpoints;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
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
        usersService.findAll().stream().map(User::getUsername).collect(Collectors.toList());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("users");
    modelAndView.addObject("users", usernames);
    return modelAndView;
  }

  // http://localhost:8080/find/admin
  @RequestMapping(value = "/find/{login}")
  public ModelAndView findUser(@PathVariable("login") String login) {
    ModelAndView modelAndView = new ModelAndView();
    //if (!bindingResult.hasErrors()) {
      modelAndView.setViewName("user");
      // ${object}
      User user = usersService.findOne(login);
      if (user != null) {
        modelAndView.addObject("user", user);
      }
    //}
    return modelAndView;
  }

  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public String logout() {
    // http://localhost:8080/login?logout
    return "redirect:/login?logout";
  }

}
