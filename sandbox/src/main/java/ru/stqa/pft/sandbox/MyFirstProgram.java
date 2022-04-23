package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    //String massage = "";
    hello("world");
    hello("user");
    hello("Toaster");

    double l = 5;
    System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

    double a = 7;
    double b = 3;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));
  }

  public static void hello(String message) {
    System.out.println("Hello, " + message + "!");
  }

  public static double area(double len) {
    return Math.pow(len, 2);
  }

  public static double area(double a, double b) {
    return a * b;
  }
}