package ua.goit.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

public class CarsDaoTest {

  private CarsDao carsDao;
  private Session session;

  @Before
  public void init() {
    SessionFactory sessionFactory = mock(SessionFactory.class);
    session = mock(Session.class);
    when(sessionFactory.getCurrentSession()).thenReturn(session);
    carsDao = new CarsDao(sessionFactory);
  }

  @Test
  public void testEmpty() {
    // if empty list, is exists - false
    Query<?> query = mock(Query.class);
    when(query.list()).thenReturn(Collections.emptyList());
    when(session.createQuery("from Cars")).thenReturn(query);
    //
    assertFalse(carsDao.isExists());
    verify(query, times(1)).list();
  }

  @Test
  public void testNotEmpty() {
    // if not empty list, is exists - true
    Query<?> query = mock(Query.class);
    when(query.list()).thenReturn(Collections.singletonList(null));
    when(session.createQuery("from Cars")).thenReturn(query);
    //
    assertTrue(carsDao.isExists());
    verify(query, times(1)).list();
  }

  @Test(expected = RuntimeException.class)
  public void testException() {
    // if not empty list, is exists - true
    Query<?> query = mock(Query.class);
    when(query.list()).thenThrow(new RuntimeException());
    when(session.createQuery("from Cars")).thenReturn(query);
    //
    carsDao.isExists();
  }


}