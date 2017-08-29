package ua.goit.online6.arithmetic;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Operations for elements provided.
 *
 * @author Andrey Minov
 */
public final class Operations {

  /**
   * Mean of provided elements.
   *
   * @param elementsSupplier the elements supplier
   * @param limit            the limit of the elements
   * @return the mean value of the provided elements.
   * @throws IllegalArgumentException in case limit <= 0
   */
  public double mean(Supplier<Double> elementsSupplier, int limit) {
    if (limit <= 0) {
      throw new IllegalArgumentException("Limit must be positive number!");
    }
    return Stream.generate(elementsSupplier).limit(limit).reduce(0D, Operators.summingDoubles())
           / limit;
  }

  /**
   * Calculate standard deviation.
   *
   * @param elementsSupplier the elements supplier
   * @param limit            the limit
   * @return the standard deviation of provided elements
   * @throws IllegalArgumentException in case limit <= 0
   */
  public Double stdDev(Supplier<Double> elementsSupplier, int limit) {
    if (limit <= 0) {
      throw new IllegalArgumentException("Limit must be positive number!");
    }
    List<Double> elements =
        Stream.generate(elementsSupplier).limit(limit).collect(Collectors.toList());
    double mean = elements.stream().reduce(0.0, Operators.summingDoubles()) / limit;
    double sqrs =
        elements.stream().map(v -> Math.pow(v - mean, 2)).reduce(0.0, Operators.summingDoubles())
        / limit;
    return Math.sqrt(sqrs);
  }
}
