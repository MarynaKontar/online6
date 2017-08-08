package ua.goit.ee.online6.qa1.simpledb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.ee.online6.qa1.simpledb.entity.Car;

@Service
public class CarService {

  private final CarDao dao;

  @Autowired
  public CarService(CarDao dao) {
    this.dao = dao;
  }

  // HTML FE -> controller (MVC, ...) (logic) -> service, HTTP, FILE -> Repository.

  @Transactional
  public void save(Car car) {
    dao.save(car);
  }

  @Transactional(readOnly = true)
  public Car get(long id) {
    return dao.get(id);
  }

  @Transactional(readOnly = true)
  public List<Car> getAll() {
    return dao.getAll();
  }
}
