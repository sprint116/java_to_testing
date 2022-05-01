package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GroupCreationFFTests {
  private WebDriver wd;
  GroupData gd = new GroupData(
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

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.get("http://localhost/addressbook/group.php");
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.id("LoginForm")).submit();
  }

  @Test
  public void testGroupCreation() {
    initGroupCreation();
    fillGroupForm(gd);
    submitGroupCreation();
    returnToGroupPage();

  }

  private void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  private void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  private void fillGroupForm(GroupData gd) {
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(gd.getGname());
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys(gd.getHeader());
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys(gd.getFooter());
  }

  private void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    wd.findElement(By.linkText("Logout")).click();
    wd.quit();
  }

}
