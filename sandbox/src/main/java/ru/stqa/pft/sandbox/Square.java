package ru.stqa.pft.sandbox;

public class Square {

  public double l;

  public Square(double l) {
    this.l = l;
    System.out.println(l);
  }

  public double area() {
    return Math.pow(this.l, 2);
  }
}
