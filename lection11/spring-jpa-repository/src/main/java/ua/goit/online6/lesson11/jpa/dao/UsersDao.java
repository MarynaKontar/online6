package ua.goit.online6.lesson11.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.online6.lesson11.jpa.entity.User;

/**
 * Data access object for {@link ua.goit.online6.lesson11.jpa.entity.User}
 *
 * @author Andrey Minov
 */
public interface UsersDao extends JpaRepository<User, String> {
}
