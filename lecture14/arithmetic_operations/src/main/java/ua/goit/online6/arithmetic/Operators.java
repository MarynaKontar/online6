package ua.goit.online6.arithmetic;

import java.util.function.BinaryOperator;

/**
 * This is factory class for creation of arithmetic suppliers.
 *
 * @author Andrey Minov
 */
public final class Operators {

  public static BinaryOperator<Double> summingDoubles() {
    return (n1, n2) -> n1 + n2;
  }

  public static BinaryOperator<Double> devideDoubles() {
    return (n1, n2) -> n1 / n2;
  }

  public static BinaryOperator<Double> multiplyDoubles() {
    return (n1, n2) -> n1 * n2;
  }
}
