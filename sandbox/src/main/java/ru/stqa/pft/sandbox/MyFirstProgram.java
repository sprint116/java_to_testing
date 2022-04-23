package ru.stqa.pft.sandbox;

import java.util.Scanner;

public class MyFirstProgram {
  /*
  public static void main(String[] args) {
    String massage = "";
    hello("world");
    hello("user");
    hello("Toaster");
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 8);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
  }

  public static void hello(String message) {
    System.out.println("Hello, " + message + "!");
  }*/

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Введите координату P1 по оси 'x': ");
    double p1x = in.nextDouble();
    System.out.print("Введите координату P1 по оси 'y': ");
    double p1y = in.nextDouble();
    System.out.print("Введите координату P2 по оси 'x': ");
    double p2x = in.nextDouble();
    System.out.print("Введите координату P2 по оси 'y': ");
    double p2y = in.nextDouble();

    System.out.println();
    System.out.printf("Введены координаты P1(%s, %s) и P2(%s, %s)\n", p1x, p1y, p2x, p2y);
    in.close();


    Point p1 = new Point(p1x, p1y);
    Point p2 = new Point(p2x, p2y);

    System.out.printf("Расстояние между точками P1 и P2 равно: %s\n", distance(p1, p2));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
  }
}