package ua.goit.ee.online6.qa11;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.ee.online6.qa11.configuration.ApplicationConfiguration;
import ua.goit.ee.online6.qa11.configuration.CacheConfiguration;
import ua.goit.ee.online6.qa11.dao.CarService;
import ua.goit.ee.online6.qa11.dao.IndexProviderService;
import ua.goit.ee.online6.qa11.entity.Car;

/**
 * Simple application which accesses data from database.
 *
 * @author Andrey Minov
 */
public class CacheApplication {

  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(CacheApplication.class);

    try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        ApplicationConfiguration.class, CacheConfiguration.class)) {
      CarService service = context.getBean(CarService.class);
      IndexProviderService indexService = context.getBean(IndexProviderService.class);

      logger.info("Staring car application!");
      // Get car index.
      logger.info("Car {} index [{}]", "VW", indexService.assignIndex("VW"));
      // Get index from cache.
      logger.info("Car {} index [{}]", "VW", indexService.assignIndex("VW"));

      // Save new car
      Car car = new Car();
      car.setName("New car");
      car.setDescription("New big car");
      service.save(car);

      // Get car from database
      car = service.getOne(car.getId());
      logger.info("Selected car: {}", car);
      // Get car from cache
      car = service.getOne(car.getId());
      logger.info("Selected car: {}", car);

      // Update car.
      service.save(car);
      // Select from database.
      car = service.getOne(car.getId());
      logger.info("Selected car: {}", car);

      List<Car> all = service.getAll();
      logger.info("Cars are {}", all);
    }
  }
}
