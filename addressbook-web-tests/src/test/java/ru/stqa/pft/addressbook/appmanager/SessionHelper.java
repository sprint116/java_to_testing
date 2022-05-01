package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {

  public WebDriver wd;

  public SessionHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void login(String username, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[3]")).click();
  }

  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }
}
