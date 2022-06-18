package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd;

  private String browser;
  private DbHelper dbHelper;
  private RegistrationHelper registrationHelper;
  private AdminHelper adminHelper;
  private UserHelper userHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private JamesHelper jamesHelper;

  public ApplicationManager(String browser){
    this.browser = browser;
    properties = new Properties();
    dbHelper = new DbHelper();
  }

  public void init()  throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
  }


  public void stop() {
    if (wd!=null) {
      wd.quit();
    }
  }

  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public AdminHelper adminHelper() {
    if (adminHelper == null) {
      adminHelper = new AdminHelper(this);
    }
    return adminHelper;
  }

  public UserHelper userHelper() {
    if (userHelper == null) {
      userHelper = new UserHelper(this);
    }
    return userHelper;
  }

  public FtpHelper ftp(){
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public WebDriver getDriver() {
    if (wd == null){
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }

      //wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }

  public MailHelper mail(){
    if (mailHelper ==null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james(){
    if (jamesHelper == null){
      jamesHelper =new  JamesHelper(this);
    }
    return jamesHelper;
  }

  public DbHelper db(){
    return dbHelper;
  }
}
