package ua.goit.ee.online6.qa1.simpledb.dao;

import java.util.List;

import ua.goit.ee.online6.qa1.simpledb.entity.Car;

/**
 * Data access object for car entity@
 */
public interface CarDao {
  /**
   * Save entity.
   *
   * @param car the car
   */
  void save(Car car);

  /**
   * Get car entity.
   *
   * @param id the id
   * @return the car
   */
  Car get(long id);

  /**
   * Gets all entities.
   *
   * @return the all
   */
  List<Car> getAll();
}
