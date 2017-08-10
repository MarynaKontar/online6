package ua.goit.ee.online6.qa11.dao;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Locally cached service for name of car.
 *
 * @author Andrey Minov
 */
@Service
public class IndexProviderService {
  // Atomically incremented counter.
  private static final AtomicInteger COUNTER = new AtomicInteger();

  // All is needed to set that service response will be cached is  @Cacheable with name of the cache.
  // Parameter will be used as key and value returned by method as value.
  // After method call cached he wont be called again.
  @Cacheable(cacheNames = "index")
  public int assignIndex(String name) {
    return COUNTER.incrementAndGet();
  }

}
