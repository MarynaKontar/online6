package ua.goit.online6.lesson11.jpa.dao;

import java.util.List;

import ua.goit.online6.lesson11.jpa.entity.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Data access object for car entity using JPA Spring DATA.
 */
interface CarDao extends JpaRepository<Car, Long> {
  // To use JPA repository you have to extend JpaRepository interface.
  List<Car> findByNameLike(String name);
  // Custom search can be added here too.
  @Query("select car from Car car where car.description like ?1")
  List<Car> findByDescriptionLike(String name);

}
