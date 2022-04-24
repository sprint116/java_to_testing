package ru.stqa.pft.sandbox.homeWork;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  Point p1 = new Point(2, 2);
  Point p2 = new Point(-1, -2);

  @Test
  public void testPoint() {
    Assert.assertEquals(p1.distance(p2), 5);
  }

  @Test
  public void testPoint2() {
    Assert.assertTrue((Math.pow(p2.x - p1.x, 2)) >= 0);
  }

  @Test
  public void testPoint3() {
    Assert.assertFalse((Math.pow(p2.y - p1.y, 2)) <= 0);
  }
}
