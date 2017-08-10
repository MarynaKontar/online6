package ua.goit.ee.online6.qa11.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.ee.online6.qa11.entity.Car;

@Service
public class CarService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

  private final CarDao dao;

  @Autowired
  public CarService(CarDao dao) {
    this.dao = dao;
  }

  // When car is saved, updated or deleted we must evict our cache.
  // We specify SPEL for key here.
  @CacheEvict(cacheNames = "cars", key = "#car.id")
  @Transactional
  public void save(Car car) {
    LOGGER.info("Saving car: {}", car);
    dao.save(car);
  }

  // When car is saved, updated or deleted we must evict our cache.
  // We specify SPEL for key here.
  @CacheEvict(cacheNames = "cars", key = "#car.id")
  @Transactional
  public void delete(Car car) {
    LOGGER.info("Delete car: {}", car);
    dao.delete(car);
  }

  // Cached cars locally also. Cache consider as key-value stored.
  @Cacheable(cacheNames = "cars")
  @Transactional
  public Car getOne(Long key) {
    LOGGER.info("Load new car from database by key {}", key);
    return dao.findOne(key);
  }

  @Transactional(readOnly = true)
  public List<Car> getAll() {
    return dao.findAll();
  }
}
