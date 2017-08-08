package ua.goit.ee.online6.qa1.simpledb.configuration;

import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main system application configuration.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"ua.goit.ee.online6.qa1.simpledb.dao"})
@PropertySource("classpath:database.properties")
public class ApplicationConfiguration {


  @Value("${db.url}")
  private String url;

  @Value("${db.username}")
  private String username;

  @Value("${db.driver}")
  private String driverClass;

  @Value("${db.hibernate_dialect}")
  private String hibernateDialect;

  @Value("${db.password}")
  private String password;

  @Value("classpath:tables_creation.sql")
  private Resource scriptResource;

  @Bean(destroyMethod = "close")
  public BasicDataSource getDatasource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(driverClass);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }

  @Bean
  public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScript(scriptResource);

    DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    initializer.setDatabasePopulator(populator);
    return initializer;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setPackagesToScan("ua.goit.ee.online6.qa1.simpledb.entity");
    sessionFactory.setDataSource(dataSource);

    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", hibernateDialect);
    sessionFactory.setHibernateProperties(properties);
    return sessionFactory;
  }

  @Bean
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    return new HibernateTransactionManager(sessionFactory);
  }
}
