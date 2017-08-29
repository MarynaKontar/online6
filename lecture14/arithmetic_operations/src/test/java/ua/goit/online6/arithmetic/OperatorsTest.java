package ua.goit.online6.arithmetic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for simple arithmetic operators.
 *
 * @author Andrey Minov
 */
public class OperatorsTest {

  @Test
  public void testSimple() {
    // 4 -> 2
    // 9 -> 3
    // -1 -> ?
    Assert.assertEquals(2, sqrt(4), 0.00001);
    Assert.assertEquals(3, sqrt(9), 0.00001);
  }

  @Test
  public void testWrong() {
    Assert.assertNotEquals(2, sqrt(5), 0.00001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBoarders() {
    sqrt(-1);
  }

  private static double sqrt(double d) {
    if (d < 0) {
      throw new IllegalArgumentException();
    }
    return Math.sqrt(d);
  }
}