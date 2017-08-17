package ua.goit.online6.authorization.pojo;

/**
 * Simple holder for user information that will be used to manipulate cars.
 *
 * @author Andrey Minov
 */
public class CarEntity {
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

  public void setDescription(String description) {
    this.description = description;
  }
}
