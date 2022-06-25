package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  public GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  public GroupData gd = new GroupData()
          .withName("TestName " + (int) (Math.random() * 100) + "/")
          .withFooter("TestFooter " + (int) (Math.random() * 100) + "/")
          .withHeader("TestHeader " + (int) (Math.random() * 100) + "/");

  //File photo = new File("src/test/resources/11.jpg");
  public ContactData cd = new ContactData()
          .withFirstName("TestName " + (int) (Math.random() * 100) + "/")
          .withLastName("LastName")
          .withNickname("testing")
          .withAddress("Testing")
          //.withPhoto(photo)
          .withHomePhone("+73333333333")
          .withMobile("+79999999999")
          .withWorkPhone("+74444444444")
          .withEmail("test@test.test")
          .withEmail2("test2@test.test")
          .withEmail3("test3@test.test")
          .withBirthdayDay("17")
          .withBirthdayMonth("January")
          .withBirthdayYear("1990")
          .withAddress2("TestingAddress")
          .withAddress2("Testing2");

  private final String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser){
    this.browser = browser;
    properties =new Properties();
  }

  public void init()  throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));

    dbHelper = new DbHelper();

    if("".equals(properties.getProperty("selenium.server"))) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
    } else {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
    }
    //wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseURL"));
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPass"));
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

  public DbHelper db(){
    return dbHelper;
  }
}
