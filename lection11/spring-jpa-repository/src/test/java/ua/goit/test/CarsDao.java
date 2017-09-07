package ua.goit.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CarsDao {
  private SessionFactory sessionFactory;

  public CarsDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Boolean isExists() {
    Session session = sessionFactory.getCurrentSession();
    List<Object> cars = session.createQuery("from Cars").list();
    return !cars.isEmpty();
  }
}
