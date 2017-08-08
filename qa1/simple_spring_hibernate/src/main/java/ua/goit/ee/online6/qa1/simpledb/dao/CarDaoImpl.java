package ua.goit.ee.online6.qa1.simpledb.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.goit.ee.online6.qa1.simpledb.entity.Car;

/**
 * Hibernate cars DAO implementation.
 *
 * @author Andrey Minov
 */
@Repository
public class CarDaoImpl implements CarDao {
  private final SessionFactory sessionFactory;

  @Autowired
  public CarDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void save(Car car) {
    Session session = sessionFactory.getCurrentSession();
    session.save(car);
  }

  public Car get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Car.class, id);
  }

  public List<Car> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return (List<Car>) session.getNamedQuery("getAll").list();
  }
}
