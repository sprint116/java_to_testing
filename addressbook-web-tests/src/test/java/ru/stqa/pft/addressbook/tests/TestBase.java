package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {
  public static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  @BeforeSuite//(alwaysRun = true)
  public void setUp() {
    app.init();
  }

  @AfterSuite//(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

}
