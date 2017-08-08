package ua.goit.ee.online6.qa1.simpledb;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.ee.online6.qa1.simpledb.configuration.ApplicationConfiguration;
import ua.goit.ee.online6.qa1.simpledb.dao.CarService;
import ua.goit.ee.online6.qa1.simpledb.entity.Car;

/**
 * Simple application which accesses data from database.
 *
 * @author Andrey Minov
 */
public class DBApplication {

  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(DBApplication.class);

    try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        ApplicationConfiguration.class)) {
      CarService service = context.getBean(CarService.class);
      logger.info("Staring car application!");

      Car car = new Car();
      car.setName("New car");
      car.setDescription("New big car");

      service.save(car);
      logger.info("New car inserted!");

      List<Car> all = service.getAll();
      logger.info("Cars are {}", all);
    }
  }
}
