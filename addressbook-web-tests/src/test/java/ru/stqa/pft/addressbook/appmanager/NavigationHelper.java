package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToMainPage() {
    click(By.linkText("home"));
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void logout() {
    click(By.linkText("Logout"));
  }

  public void gotoContactsPage() {
    click(By.linkText("home page"));
  }
}
