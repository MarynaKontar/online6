package ua.goit.online6.lesson11.jpa.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main system application configuration.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"ua.goit.online6.lesson11.dao"})
@PropertySource("classpath:database.properties")
// To enable JPA data support annotation @EnableJpaRepositories is used!
// Parameters inside indicate packages where DAOs is located.
@EnableJpaRepositories("ua.goit.online6.lesson11.dao")
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

  // Initial step everytime is create new datasource for database connection
  @Bean(destroyMethod = "close")
  public BasicDataSource getDatasource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(driverClass);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }

  /**
   * Create now bean as wrapper over {@link javax.persistence.EntityManagerFactory}
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    /// Create vendor adapter for JPA. In current case Hibernate.
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setShowSql(true);
    hibernateJpaVendorAdapter.setDatabasePlatform(hibernateDialect);
    // Create wrapper bean.
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    // Set datasource to entity manager factory. This will allow it to connect to database.
    bean.setDataSource(dataSource);
    // Set to wrapper specific vendor properties.
    bean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
    // Set packages where our JPA entyties are located.
    bean.setPackagesToScan("ua.goit.online6.lesson11.entity");
    return bean;
  }

  // After EntityManagerFactory is set we have to tell Spring how to handle transactions
  // by creation of PlatformTransactionManager
  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }


  // This is bean responsible to population of HSQL Db on start.
  @Bean
  public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScript(scriptResource);

    DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    initializer.setDatabasePopulator(populator);
    return initializer;
  }
}
