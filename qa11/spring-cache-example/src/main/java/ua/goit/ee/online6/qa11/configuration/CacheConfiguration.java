package ua.goit.ee.online6.qa11.configuration;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring cache configuration.
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

  // Only need for caching is to define how to deal with caches.
  // There are lots of cache implementation : ehcache, in-memory
  // All of then register in cache manager.
  // This is manager for all the caches.
  @Bean
  public CacheManager cacheManager() {
    // configure and return an implementation of Spring's CacheManager SPI
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    cacheManager.setCaches(Arrays
        .asList(new ConcurrentMapCache("default"), new ConcurrentMapCache("cars"),
            new ConcurrentMapCache("index")));
    return cacheManager;
  }
}
