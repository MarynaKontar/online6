package ua.goit.online6.lesson11.jpa.dao;

import java.util.List;

import ua.goit.online6.lesson11.jpa.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Service for holding information about cars must be created.
@Service
public class CarService {

  private final CarDao dao;

  // JPA implementation of CarDao will be automatically injected here.
  @Autowired
  public CarService(CarDao dao) {
    this.dao = dao;
  }

  // All methods that will be used transaction / DB connections must be annotated by @Transaction annotation.
  @Transactional(readOnly = true)
  public List<Car> findByNameLike(String name) {
    return dao.findByNameLike(name);
  }

  /**
   * Transactional annotation will be handled by {@link org.springframework.transaction.interceptor.TransactionInterceptor}
   * and new Session (in case hibernate is using) will be set into SessionFactory.currentSession
   * of Entity manager when JPA is used.
   */
  @Transactional(readOnly = true)
  public List<Car> findByDescriptionLike(String name) {
    return dao.findByDescriptionLike(name);
  }

  @Transactional(readOnly = true)
  public List<Car> findAll() {
    return dao.findAll();
  }

  @Transactional(readOnly = true)
  public Car getOne(Long aLong) {
    return dao.getOne(aLong);
  }

  // Flag readOnly indicates spring transaction should it start new transaction or join existed when enter current methods.
  // Remember! Read only operations must not block any resources!
  @Transactional
  public Car save(Car entity) {
    return dao.save(entity);
  }

  @Transactional
  public void delete(Car entity) {
    dao.delete(entity);
  }
}
