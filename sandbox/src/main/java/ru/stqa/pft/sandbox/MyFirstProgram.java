package ru.stqa.pft.sandbox;

public class MyFirstProgram {
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
  }
}