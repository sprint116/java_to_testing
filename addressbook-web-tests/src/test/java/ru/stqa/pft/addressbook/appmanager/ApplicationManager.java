package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  public GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  public GroupData gd = new GroupData().withName("TestName " + (int) (Math.random() * 100) + "/");


  public ContactData cd = new ContactData()
          .withFirstName("TestName " + (int) (Math.random() * 100) + "/")
          .withLastName("LastName")
          .withNickname("testing")
          .withAddress("Testing")
          .withMobile("+79999999999")
          .withEmail("test@test.test")
          .withBirthdayDay("17")
          .withBirthdayMonth("January")
          .withBirthdayYear("1990")
          .withAddress2("TestingAddress");
  private final String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }

    //wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    sessionHelper.logout();
    wd.quit();
  }


  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
}
