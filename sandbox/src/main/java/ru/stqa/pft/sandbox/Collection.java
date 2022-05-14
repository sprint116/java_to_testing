package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collection {
  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "PHP", "Python"};

    List<String> languages = Arrays.asList("Java", "C#", "PHP", "Python");

    for (String l : languages) {
      System.out.println("Я хочу выучить язык " + l);
    }
  }
}
