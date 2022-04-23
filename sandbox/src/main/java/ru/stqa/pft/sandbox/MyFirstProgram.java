package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    //String massage = "";
    hello("world");
    hello("user");
    hello("Toaster");
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + area(s));

    Rectangle r = new Rectangle(4, 8);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));
  }

  public static void hello(String message) {
    System.out.println("Hello, " + message + "!");
  }

  public static double area(Square s) {
    return Math.pow(s.l, 2);
  }

  public static double area(Rectangle r) {
    return r.a * r.b;
  }
}