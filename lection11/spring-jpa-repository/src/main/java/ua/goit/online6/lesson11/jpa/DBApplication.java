package ua.goit.online6.lesson11.jpa;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.online6.lesson11.jpa.configuration.ModelConfiguration;
import ua.goit.online6.lesson11.jpa.dao.CarService;
import ua.goit.online6.lesson11.jpa.entity.Car;

/**
 * Simple application which accesses data from database.
 *
 * @author Andrey Minov
 */
public class DBApplication {

  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(DBApplication.class);

    try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        ModelConfiguration.class)) {
      CarService service = context.getBean(CarService.class);
      logger.info("Staring car application!");

      Car car = new Car();
      car.setName("New car");
      car.setDescription("New big car");

      service.save(car);
      logger.info("New car inserted!");

      List<Car> all = service.findAll();
      logger.info("Cars are {}", all);

      List<Car> result = service.findByDescriptionLike("%car%");
      logger.info("Cars like %car% are {}", result);
    }
  }
}
