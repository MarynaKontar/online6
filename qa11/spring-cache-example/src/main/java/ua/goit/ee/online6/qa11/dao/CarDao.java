package ua.goit.ee.online6.qa11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.ee.online6.qa11.entity.Car;

/**
 * Data access object for car entity@
 */
public interface CarDao extends JpaRepository<Car, Long> {
}
