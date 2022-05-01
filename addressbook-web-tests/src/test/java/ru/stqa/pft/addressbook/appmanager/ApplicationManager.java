package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;
  private NavigationHelper navigationHelper;
  private ContactHellper contactHellper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  public GroupData gd = new GroupData(
          "Test1",
          "Test1",
          "Test1",
          "TestName",
          "LastName",
          "testing",
          "Testing",
          "+79999999999",
          "test@test.test",
          "17",
          "January",
          "1990",
          "TestingAddress"
  );



  public void init() {
    wd = new ChromeDriver();
    //wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook");
    groupHelper = new GroupHelper(wd);
    contactHellper = new ContactHellper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {

    wd.quit();
  }

  public void returnToMainPage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHellper getContactHellper() {
    return contactHellper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
