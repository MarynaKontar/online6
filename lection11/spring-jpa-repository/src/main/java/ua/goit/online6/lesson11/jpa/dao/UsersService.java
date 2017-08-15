package ua.goit.online6.lesson11.jpa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.online6.lesson11.jpa.entity.User;

/**
 * Service for manipulation of users.
 *
 * @author Andrey Minov
 */
@Service
public class UsersService {

  private final UsersDao usersDao;

  @Autowired
  public UsersService(UsersDao usersDao) {
    this.usersDao = usersDao;
  }

  @Transactional(readOnly = true)
  public List<User> findAll() {
    return usersDao.findAll();
  }

  @Transactional(readOnly = true)
  public User findOne(String s) {
    return usersDao.findOne(s);
  }

  @Transactional
  public User save(User entity) {
    return usersDao.save(entity);
  }

  @Transactional
  public void delete(User entity) {
    usersDao.delete(entity);
  }
}
