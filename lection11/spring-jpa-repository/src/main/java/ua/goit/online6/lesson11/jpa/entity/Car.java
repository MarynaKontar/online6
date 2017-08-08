package ua.goit.online6.lesson11.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity for car abstraction.
 *
 * @author Andrey Minov
 */
@Entity
@Table(name = "cars")
@NamedQueries( {@NamedQuery(name = "getAll", query = "from Car")})
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  @Column(name = "description")
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Car{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\''
           + '}';
  }
}
