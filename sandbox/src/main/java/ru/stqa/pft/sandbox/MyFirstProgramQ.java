package ru.stqa.pft.sandbox;

import java.util.Scanner;

public class MyFirstProgramQ {

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

    System.out.printf("Расстояние между точками P1 и P2 равно: %s\n", p1.distance(p2));
  }
}