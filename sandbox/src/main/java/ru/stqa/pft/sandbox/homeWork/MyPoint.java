package ru.stqa.pft.sandbox.homeWork;

public class MyPoint {

  public static void main(String[] args) {
    pointPosition();
  }

  public static void pointPosition() {
    Point p1 = new Point(-1.5, 2);
    Point p2 = new Point(1, -5.5);
    System.out.println(p1.x + " " + p2);
    System.out.printf("Расстояние между точками P1 и P2 равно: %s\n", p1.distance(p2));
  }
}